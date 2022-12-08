package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.CategoryBrandRelationDao;
import cloud.stackexplode.gulimall.product.dao.CategoryDao;
import cloud.stackexplode.gulimall.common.entities.product.entity.CategoryBrandRelationEntity;
import cloud.stackexplode.gulimall.common.entities.product.entity.CategoryEntity;
import cloud.stackexplode.gulimall.product.service.CategoryService;
import cloud.stackexplode.gulimall.common.vo.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity>
    implements CategoryService {
  @Autowired private CategoryBrandRelationDao categoryBrandRelationDao;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryEntity> page =
        this.page(new Query<CategoryEntity>().getPage(params), new QueryWrapper<CategoryEntity>());
    return new PageUtils(page);
  }

  @Override
  @Cacheable(value = "category", key = "#root.methodName",sync = true)
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
    // TODO 检查菜单是否被应用
    return baseMapper.deleteBatchIds(ids) == ids.size();
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
            .sorted(Comparator.comparing(CategoryEntity::getSort))
            .collect(Collectors.toList());

    return childrens;
  }

  @Override
  public Long[] findCatelogPath(Long id) {
    List<CategoryEntity> allPathCategories = new ArrayList<>(3);
    findAllPath(getById(id), allPathCategories);
    Long[] allPath = new Long[3];
    List<Long> list =
        allPathCategories.stream().map(CategoryEntity::getCatId).collect(Collectors.toList());
    Collections.reverse(list);
    return list.toArray(allPath);
  }

  private void findAllPath(CategoryEntity categoryEntity, List<CategoryEntity> path) {
    if (categoryEntity != null && categoryEntity.getParentCid() >= 0) {
      path.add(categoryEntity);
      findAllPath(getById(categoryEntity.getParentCid()), path);
    }
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public int updateDetail(CategoryEntity category) {
    this.updateById(category);
    QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("catelog_id", category.getCatId());
    CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
    categoryBrandRelationEntity.setCatelogName(category.getName());
    return categoryBrandRelationDao.update(categoryBrandRelationEntity, queryWrapper);
  }

  @Override
  public List<CategoryEntity> getLevel1Categories() {
    return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
  }

  @Override
  public Map<String, List<Catelog2Vo>> getCatelogJsonDbWithSpringCache() {
    List<CategoryEntity> categoryEntities =
        this.list(new QueryWrapper<CategoryEntity>().eq("cat_level", 2));
    List<Catelog2Vo> catelog2Vos =
        categoryEntities.stream()
            .map(
                categoryEntity -> {
                  List<CategoryEntity> level3 =
                      this.list(
                          new QueryWrapper<CategoryEntity>()
                              .eq("parent_cid", categoryEntity.getCatId()));
                  List<Catelog2Vo.Catelog3Vo> catelog3Vos =
                      level3.stream()
                          .map(
                              cat ->
                                  new Catelog2Vo.Catelog3Vo(
                                      cat.getParentCid().toString(),
                                      cat.getCatId().toString(),
                                      cat.getName()))
                          .collect(Collectors.toList());
                  return new Catelog2Vo(
                      categoryEntity.getParentCid().toString(),
                      categoryEntity.getCatId().toString(),
                      categoryEntity.getName(),
                      catelog3Vos);
                })
            .collect(Collectors.toList());
    Map<String, List<Catelog2Vo>> catelogMap = new HashMap<>();
    for (Catelog2Vo catelog2Vo : catelog2Vos) {
      List<Catelog2Vo> list =
          catelogMap.getOrDefault(catelog2Vo.getCatelog1Id(), new LinkedList<>());
      list.add(catelog2Vo);
      catelogMap.put(catelog2Vo.getCatelog1Id(), list);
    }
    return catelogMap;
  }
}
