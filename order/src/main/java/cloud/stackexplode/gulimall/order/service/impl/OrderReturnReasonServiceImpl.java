package cloud.stackexplode.gulimall.order.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.order.dao.OrderReturnReasonDao;
import cloud.stackexplode.gulimall.common.entities.order.entity.OrderReturnReasonEntity;
import cloud.stackexplode.gulimall.order.service.OrderReturnReasonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl
        extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity>
        implements OrderReturnReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnReasonEntity> page =
                this.page(
                        new Query<OrderReturnReasonEntity>().getPage(params),
                        new QueryWrapper<OrderReturnReasonEntity>());

        return new PageUtils(page);
    }
}
