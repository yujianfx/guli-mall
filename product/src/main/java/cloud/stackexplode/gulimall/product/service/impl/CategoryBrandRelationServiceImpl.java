package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.BrandDao;
import cloud.stackexplode.gulimall.product.dao.CategoryBrandRelationDao;
import cloud.stackexplode.gulimall.product.dao.CategoryDao;
import cloud.stackexplode.gulimall.common.entities.product.entity.CategoryBrandRelationEntity;
import cloud.stackexplode.gulimall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl
    extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity>
    implements CategoryBrandRelationService {
  @Autowired private CategoryDao categoryDao;
  @Autowired private BrandDao brandDao;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryBrandRelationEntity> page =
        this.page(
            new Query<CategoryBrandRelationEntity>().getPage(params),
            new QueryWrapper<CategoryBrandRelationEntity>());

    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPageByBrandId(Map<String, Object> params, Long bId) {
    QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("brand_id", bId);
    IPage<CategoryBrandRelationEntity> page =
        this.page(new Query<CategoryBrandRelationEntity>().getPage(params), queryWrapper);
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPageByCid(Map<String, Object> params, Long cId) {
    QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("catelog_id", cId);
    IPage<CategoryBrandRelationEntity> page =
        this.page(new Query<CategoryBrandRelationEntity>().getPage(params), queryWrapper);
    return new PageUtils(page);
  }

  @Override
  public boolean saveDetil(CategoryBrandRelationEntity categoryBrandRelation) {
    Long categoryId = categoryBrandRelation.getCatelogId();
    Long brandId = categoryBrandRelation.getBrandId();
    String cname = categoryDao.selectById(categoryId).getName();
    String bname = brandDao.selectById(brandId).getName();
    categoryBrandRelation.setBrandName(bname);
    categoryBrandRelation.setCatelogName(cname);
    this.save(categoryBrandRelation);
    return false;
  }
}
