package top.wangudiercai.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.order.dao.OrderOperateHistoryDao;
import top.wangudiercai.gulimall.order.entity.OrderOperateHistoryEntity;
import top.wangudiercai.gulimall.order.service.OrderOperateHistoryService;

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
