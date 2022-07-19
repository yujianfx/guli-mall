package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.CouponSpuRelationDao;
import top.wangudiercai.gulimall.coupon.entity.CouponSpuRelationEntity;
import top.wangudiercai.gulimall.coupon.service.CouponSpuRelationService;

@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl
    extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity>
    implements CouponSpuRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponSpuRelationEntity> page =
        this.page(
            new Query<CouponSpuRelationEntity>().getPage(params),
            new QueryWrapper<CouponSpuRelationEntity>());

    return new PageUtils(page);
  }
}
