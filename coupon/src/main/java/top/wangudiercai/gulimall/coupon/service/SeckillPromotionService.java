package top.wangudiercai.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
