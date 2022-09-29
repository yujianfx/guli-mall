package cloud.stackexplode.gulimall.product.dao;

import cloud.stackexplode.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
}
