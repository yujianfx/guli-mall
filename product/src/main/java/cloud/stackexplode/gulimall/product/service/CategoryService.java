package cloud.stackexplode.gulimall.product.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listTree();

    boolean removeMenuByIds(Collection<Long> ids);

    Long[] findCatelogPath(Long id);

    int updateDetail(CategoryEntity category);
}
