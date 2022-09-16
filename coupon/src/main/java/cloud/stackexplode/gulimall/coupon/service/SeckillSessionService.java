package cloud.stackexplode.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
