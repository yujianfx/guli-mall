package cloud.stackexplode.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
