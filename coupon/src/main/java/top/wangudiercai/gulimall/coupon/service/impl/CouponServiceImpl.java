package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.CouponDao;
import top.wangudiercai.gulimall.coupon.entity.CouponEntity;
import top.wangudiercai.gulimall.coupon.service.CouponService;

@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity>
    implements CouponService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponEntity> page =
        this.page(new Query<CouponEntity>().getPage(params), new QueryWrapper<CouponEntity>());

    return new PageUtils(page);
  }
}
