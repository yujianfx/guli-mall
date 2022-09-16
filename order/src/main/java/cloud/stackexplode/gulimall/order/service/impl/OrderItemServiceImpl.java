package cloud.stackexplode.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.order.dao.OrderItemDao;
import cloud.stackexplode.gulimall.order.entity.OrderItemEntity;
import cloud.stackexplode.gulimall.order.service.OrderItemService;

@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity>
    implements OrderItemService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderItemEntity> page =
        this.page(
            new Query<OrderItemEntity>().getPage(params), new QueryWrapper<OrderItemEntity>());

    return new PageUtils(page);
  }
}
