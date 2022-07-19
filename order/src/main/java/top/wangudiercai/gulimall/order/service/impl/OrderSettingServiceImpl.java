package top.wangudiercai.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.order.dao.OrderSettingDao;
import top.wangudiercai.gulimall.order.entity.OrderSettingEntity;
import top.wangudiercai.gulimall.order.service.OrderSettingService;

@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingDao, OrderSettingEntity>
    implements OrderSettingService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderSettingEntity> page =
        this.page(
            new Query<OrderSettingEntity>().getPage(params),
            new QueryWrapper<OrderSettingEntity>());

    return new PageUtils(page);
  }
}
