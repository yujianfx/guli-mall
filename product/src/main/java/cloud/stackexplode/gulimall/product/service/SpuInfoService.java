package cloud.stackexplode.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
