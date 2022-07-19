package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.CategoryBrandRelationDao;
import top.wangudiercai.gulimall.product.entity.CategoryBrandRelationEntity;
import top.wangudiercai.gulimall.product.service.CategoryBrandRelationService;

@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl
    extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity>
    implements CategoryBrandRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryBrandRelationEntity> page =
        this.page(
            new Query<CategoryBrandRelationEntity>().getPage(params),
            new QueryWrapper<CategoryBrandRelationEntity>());

    return new PageUtils(page);
  }
}
