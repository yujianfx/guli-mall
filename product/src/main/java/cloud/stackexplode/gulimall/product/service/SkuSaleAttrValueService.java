package cloud.stackexplode.gulimall.product.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.entities.product.entity.SkuSaleAttrValueEntity;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuItemSaleAttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> listSaleAttrs(Long spuId);

    List<String> getSkuSaleAttrValuesAsString(Long skuId);
}
