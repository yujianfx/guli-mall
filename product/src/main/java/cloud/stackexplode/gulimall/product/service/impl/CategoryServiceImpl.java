package cloud.stackexplode.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.product.dao.CategoryDao;
import cloud.stackexplode.gulimall.product.entity.CategoryEntity;
import cloud.stackexplode.gulimall.product.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity>
    implements CategoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryEntity> page =
        this.page(new Query<CategoryEntity>().getPage(params), new QueryWrapper<CategoryEntity>());

    return new PageUtils(page);
  }

  @Override
  public List<CategoryEntity> listTree() {
    List<CategoryEntity> list = baseMapper.selectList(null);
    List<CategoryEntity> allLevel1 =
        list.stream()
            .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
            .map(
                categoryEntity -> {
                  categoryEntity.setChildren(getAllChildren(list, categoryEntity));
                  return categoryEntity;
                })
            .collect(Collectors.toList());

    return allLevel1;
  }

    @Override
    public boolean removeMenuByIds(Collection<Long> ids) {
      //TODO 检查菜单是否被应用
        return baseMapper.deleteBatchIds(ids)==ids.size();
    }

    private List<CategoryEntity> getAllChildren(List<CategoryEntity> all, CategoryEntity root) {
    List<CategoryEntity> childrens =
        all.stream()
            .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
            .map(
                categoryEntity -> {
                  categoryEntity.setChildren(getAllChildren(all, categoryEntity));
                  return categoryEntity;
                })
            .sorted(
                (categoryEntity1, categoryEntity2) ->
                    categoryEntity1.getSort().compareTo(categoryEntity2.getSort()))
            .collect(Collectors.toList());

    return childrens;
  }
}
