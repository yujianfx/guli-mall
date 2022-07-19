package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.CouponSpuCategoryRelationDao;
import top.wangudiercai.gulimall.coupon.entity.CouponSpuCategoryRelationEntity;
import top.wangudiercai.gulimall.coupon.service.CouponSpuCategoryRelationService;

@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl
    extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity>
    implements CouponSpuCategoryRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponSpuCategoryRelationEntity> page =
        this.page(
            new Query<CouponSpuCategoryRelationEntity>().getPage(params),
            new QueryWrapper<CouponSpuCategoryRelationEntity>());

    return new PageUtils(page);
  }
}
