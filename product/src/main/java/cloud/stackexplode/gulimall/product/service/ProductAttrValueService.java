package cloud.stackexplode.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;

import java.util.Map;

/**
 * spu属性值
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
