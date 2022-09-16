package cloud.stackexplode.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
