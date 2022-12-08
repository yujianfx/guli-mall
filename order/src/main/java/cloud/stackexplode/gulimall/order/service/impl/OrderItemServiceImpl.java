package cloud.stackexplode.gulimall.order.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.order.dao.OrderItemDao;
import cloud.stackexplode.gulimall.common.entities.order.entity.OrderItemEntity;
import cloud.stackexplode.gulimall.order.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
