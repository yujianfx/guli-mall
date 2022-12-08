package cloud.stackexplode.gulimall.product.dao;

import cloud.stackexplode.gulimall.common.entities.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.common.vo.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu属性值
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {
    List<SpuItemAttrGroupVo> getProductGroupAttrsBySpuId(@Param("spuId") Long spuId, @Param("catelogId") Long catelogId);
}
