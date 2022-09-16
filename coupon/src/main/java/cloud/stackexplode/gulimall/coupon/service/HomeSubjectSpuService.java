package cloud.stackexplode.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
