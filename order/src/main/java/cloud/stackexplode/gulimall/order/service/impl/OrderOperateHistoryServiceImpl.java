package cloud.stackexplode.gulimall.order.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.order.dao.OrderOperateHistoryDao;
import cloud.stackexplode.gulimall.common.entities.order.entity.OrderOperateHistoryEntity;
import cloud.stackexplode.gulimall.order.service.OrderOperateHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl
        extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity>
        implements OrderOperateHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperateHistoryEntity> page =
                this.page(
                        new Query<OrderOperateHistoryEntity>().getPage(params),
                        new QueryWrapper<OrderOperateHistoryEntity>());

        return new PageUtils(page);
    }
}
