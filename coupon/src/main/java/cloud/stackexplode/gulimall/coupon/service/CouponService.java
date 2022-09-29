package cloud.stackexplode.gulimall.coupon.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
