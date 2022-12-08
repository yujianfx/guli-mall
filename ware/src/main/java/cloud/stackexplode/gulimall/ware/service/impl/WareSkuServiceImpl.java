package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.entities.ware.entity.WareOrderTaskDetailEntity;
import cloud.stackexplode.gulimall.common.entities.ware.entity.WareOrderTaskEntity;
import cloud.stackexplode.gulimall.common.entities.ware.entity.WareSkuEntity;
import cloud.stackexplode.gulimall.common.enums.order.OrderStatus;
import cloud.stackexplode.gulimall.common.enums.ware.WareTaskDetailStatus;
import cloud.stackexplode.gulimall.common.enums.ware.WareTaskStatus;
import cloud.stackexplode.gulimall.common.exception.ware.NoStockException;
import cloud.stackexplode.gulimall.common.to.mq.StockDetailTo;
import cloud.stackexplode.gulimall.common.to.mq.StockLockedTo;
import cloud.stackexplode.gulimall.common.to.order.OrderTo;
import cloud.stackexplode.gulimall.common.to.product.SkuHasStockVo;
import cloud.stackexplode.gulimall.common.to.product.SkuInfoTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.OrderItemVo;
import cloud.stackexplode.gulimall.common.vo.order.vo.WareSkuLockVo;
import cloud.stackexplode.gulimall.ware.dao.WareSkuDao;
import cloud.stackexplode.gulimall.ware.feign.OrderFeignService;
import cloud.stackexplode.gulimall.ware.feign.ProductFeignService;
import cloud.stackexplode.gulimall.ware.service.WareOrderTaskDetailService;
import cloud.stackexplode.gulimall.ware.service.WareOrderTaskService;
import cloud.stackexplode.gulimall.ware.service.WareSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("wareSkuService")
@Slf4j
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity>
        implements WareSkuService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private WareOrderTaskService wareOrderTaskService;

    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public List<WareSkuEntity> getListBySkuId(List<Long> id) {
        return this.list(new QueryWrapper<WareSkuEntity>().in("sku_id", id));
    }

    @TransactionalEventListener
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void commit(Long orderSn) {
        WareOrderTaskEntity wareOrderTask = wareOrderTaskService
                .getOne(new QueryWrapper<WareOrderTaskEntity>()
                        .eq("order_sn", orderSn));
        Long taskId = wareOrderTask.getId();
        List<WareOrderTaskDetailEntity> wareOrderTaskDetailEntities = wareOrderTaskDetailService
                .list(new QueryWrapper<WareOrderTaskDetailEntity>()
                        .eq("task_id", taskId));
        wareOrderTaskDetailEntities
                .stream()
                .parallel()
                .forEach(wareOrderTaskDetailEntity -> {
                    this.lambdaUpdate().setSql("stock = stock - " + wareOrderTaskDetailEntity.getSkuNum())
                            .setSql("stock_locked=stock_locked+" + wareOrderTaskDetailEntity.getSkuNum())
                            .eq(WareSkuEntity::getSkuId, wareOrderTaskDetailEntity.getSkuId())
                            .eq(WareSkuEntity::getWareId, wareOrderTaskDetailEntity.getWareId())
                            .update();

                });
        wareOrderTaskDetailEntities.forEach(wareOrderTaskDetailEntity -> {
            wareOrderTaskDetailEntity.setLockStatus(WareTaskDetailStatus.SUBMITED);
            wareOrderTaskDetailService.updateById(wareOrderTaskDetailEntity);
        });
        wareOrderTaskService.lambdaUpdate().set(WareOrderTaskEntity::getTaskStatus, WareTaskStatus.SUBMITED)
                .eq(WareOrderTaskEntity::getId, taskId)
                .update();
        wareOrderTaskDetailService.updateBatchById(wareOrderTaskDetailEntities);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String skuName = (String) params.get("skuName");
        String wareId = (String) params.get("wareId");
        QueryWrapper<WareSkuEntity> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(skuName)) {
            wareInfoEntityQueryWrapper.like("sku_name", skuName);
        }
        if (StringUtils.isNotEmpty(wareId)) {
            wareInfoEntityQueryWrapper.eq("ware_id", wareId);
        }
        IPage<WareSkuEntity> page =
                this.page(new Query<WareSkuEntity>().getPage(params), wareInfoEntityQueryWrapper);
        return new PageUtils(page);
    }

    @Override
    public WareSkuEntity getBySkuId(Long id) {
        return baseMapper.selectOne(new QueryWrapper<WareSkuEntity>().eq("sku_id", id));
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        Integer count = this.baseMapper.selectCount(
                new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (count == 0) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);

            try {
                R<SkuInfoTo> info = productFeignService.getSkuInfo(skuId);
                wareSkuEntity.setSkuName((info.getData().getSkuName()));
            } catch (Exception ignored) {
            }
            this.baseMapper.insert(wareSkuEntity);
        } else {
            this.baseMapper.addstock(skuId, wareId, skuNum);
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStocks(List<Long> ids) {
        return ids.stream().map(id -> {
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(id);
            Integer count = baseMapper.getTotalStock(id);
            skuHasStockVo.setHasStock(count != null && count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean orderLockStock(WareSkuLockVo wareSkuLockVo) {
        //因为可能出现订单回滚后，库存锁定不回滚的情况，但订单已经回滚，得不到库存锁定信息，因此要有库存工作单
        WareOrderTaskEntity taskEntity = new WareOrderTaskEntity();
        taskEntity.setOrderSn(wareSkuLockVo.getOrderSn());
        taskEntity.setCreateTime(new Date());
        wareOrderTaskService.save(taskEntity);
        log.info("库存工作单创建成功，工作单号：{}", taskEntity.getId());
        List<OrderItemVo> itemVos = wareSkuLockVo.getLocks();
        List<SkuLockVo> lockVos = itemVos.stream().map((item) -> {
            SkuLockVo skuLockVo = new SkuLockVo();
            skuLockVo.setSkuId(item.getSkuId());
            skuLockVo.setNum(item.getCount());
            //找出所有库存大于商品数的仓库
            List<Long> wareIds = baseMapper.listWareIdsHasStock(item.getSkuId(), item.getCount());
            skuLockVo.setWareIds(wareIds);
            return skuLockVo;
        }).collect(Collectors.toList());

        for (SkuLockVo lockVo : lockVos) {
            boolean lock = true;
            Long skuId = lockVo.getSkuId();
            List<Long> wareIds = lockVo.getWareIds();
            //如果没有满足条件的仓库，抛出异常
            if (wareIds == null || wareIds.size() == 0) {
                throw new NoStockException(skuId);
            } else {
                for (Long wareId : wareIds) {
                    Long count = baseMapper.lockWareSku(skuId, lockVo.getNum(), wareId);
                    if (count == 0) {
                        lock = false;
                    } else {
                        WareOrderTaskDetailEntity detailEntity = new WareOrderTaskDetailEntity();
                        detailEntity.setSkuId(skuId)
                                .setSkuNum(lockVo.getNum())
                                .setTaskId(taskEntity.getId())
                                .setWareId(wareId)
                                .setLockStatus(WareTaskDetailStatus.LOCKED);
                        //锁定成功，保存工作单详情
                        wareOrderTaskDetailService.save(detailEntity);
                        //发送库存锁定消息至延迟队列
                        StockLockedTo lockedTo = new StockLockedTo();
                        lockedTo.setId(taskEntity.getId());
                        StockDetailTo detailTo = new StockDetailTo();
                        BeanUtils.copyProperties(detailEntity, detailTo);
                        lockedTo.setDetailTo(detailTo);
                        lock = true;
                        break;
                    }
                }
            }
            if (!lock) {
                throw new NoStockException(skuId);
            }
        }
        rabbitTemplate.convertAndSend("ware.event.exchange","ware.event.delay.lock", taskEntity.getId());
        return true;
    }

    /**
     * 1、没有这个订单，必须解锁库存
     * *          2、有这个订单，不一定解锁库存
     * *              订单状态：已取消：解锁库存
     * *                      已支付：不能解锁库存
     * 消息队列解锁库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlock(Long taskId) {
        WareOrderTaskEntity wareOrderTaskEntity = wareOrderTaskService.getById(taskId);
        if (ObjectUtils.isNotEmpty(wareOrderTaskEntity)) {
            R<OrderTo> orderTo = orderFeignService.getOrderTo(wareOrderTaskEntity.getOrderSn());
            if (ObjectUtils.isEmpty(orderTo) && orderTo.getData().getStatus().equals(OrderStatus.CANCLED)) {
                wareOrderTaskDetailService
                        .getWareOrderTaskDetailByTaskId(taskId)
                        .forEach((detail) -> {
                            Long skuId = detail.getSkuId();
                            Integer num = detail.getSkuNum();
                            Long wareId = detail.getWareId();
                            if (detail.getLockStatus().equals(WareTaskDetailStatus.LOCKED)) {
                                baseMapper.unlockStock(skuId, num, wareId);
                                wareOrderTaskDetailService.updateById(detail.setLockStatus(WareTaskDetailStatus.UNLOCKED));
                            }
                        });
            }

        }
    }

    @Override
    public void unlock(Long orderSn) {
        WareOrderTaskEntity taskEntity = wareOrderTaskService.getBaseMapper().selectOne((new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn)));
        //查询出当前订单相关的且处于锁定状态的工作单详情
        List<WareOrderTaskDetailEntity> lockDetails = wareOrderTaskDetailService.list(new QueryWrapper<WareOrderTaskDetailEntity>().eq("task_id", taskEntity.getId()).eq("lock_status", WareTaskDetailStatus.LOCKED));
        for (WareOrderTaskDetailEntity lockDetail : lockDetails) {
            unlockStock(lockDetail.getSkuId(), lockDetail.getSkuNum(), lockDetail.getWareId(), lockDetail.getId());
        }
    }

    private void unlockStock(Long skuId, Integer skuNum, Long wareId, Long detailId) {
        //数据库中解锁库存数据
        baseMapper.unlockStock(skuId, skuNum, wareId);
        //更新库存工作单详情的状态
        WareOrderTaskDetailEntity detailEntity = new WareOrderTaskDetailEntity().setId(detailId).setLockStatus(WareTaskDetailStatus.UNLOCKED);
        wareOrderTaskDetailService.updateById(detailEntity);
    }

    @Data
    static
    class SkuLockVo {
        private Long skuId;
        private Integer num;
        private List<Long> wareIds;
    }


}
