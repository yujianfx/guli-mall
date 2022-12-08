package cloud.stackexplode.gulimall.product.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.entities.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveDetil(CategoryBrandRelationEntity categoryBrandRelation);

    PageUtils queryPageByBrandId(Map<String, Object> params, Long bId);

    PageUtils queryPageByCid(Map<String, Object> params, Long cId);
}
