package cloud.stackexplode.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
