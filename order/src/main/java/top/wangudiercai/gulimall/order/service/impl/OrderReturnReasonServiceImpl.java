package top.wangudiercai.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.order.dao.OrderReturnReasonDao;
import top.wangudiercai.gulimall.order.entity.OrderReturnReasonEntity;
import top.wangudiercai.gulimall.order.service.OrderReturnReasonService;

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
