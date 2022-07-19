package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.CouponHistoryDao;
import top.wangudiercai.gulimall.coupon.entity.CouponHistoryEntity;
import top.wangudiercai.gulimall.coupon.service.CouponHistoryService;

@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity>
    implements CouponHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponHistoryEntity> page =
        this.page(
            new Query<CouponHistoryEntity>().getPage(params),
            new QueryWrapper<CouponHistoryEntity>());

    return new PageUtils(page);
  }
}
