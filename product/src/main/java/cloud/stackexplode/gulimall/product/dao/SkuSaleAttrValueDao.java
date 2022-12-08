package cloud.stackexplode.gulimall.product.dao;

import cloud.stackexplode.gulimall.common.entities.product.entity.SkuSaleAttrValueEntity;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuItemSaleAttrVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {
  List<SkuItemSaleAttrVo> listSaleAttrs(@Param("spuId") Long spuId);

  List<String> getSkuSaleAttrValuesAsString(@Param("skuId") Long skuId);
}
