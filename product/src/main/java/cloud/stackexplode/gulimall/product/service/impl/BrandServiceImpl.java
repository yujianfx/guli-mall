package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.BrandDao;
import cloud.stackexplode.gulimall.product.dao.CategoryBrandRelationDao;
import cloud.stackexplode.gulimall.product.entity.BrandEntity;
import cloud.stackexplode.gulimall.product.entity.CategoryBrandRelationEntity;
import cloud.stackexplode.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        return StringUtils.isNotEmpty(key)
                ? new PageUtils(
                this.page(
                        new Query<BrandEntity>().getPage(params),
                        new QueryWrapper<BrandEntity>().like("name", key)))
                : new PageUtils(this.page(new Query<BrandEntity>().getPage(params), new QueryWrapper<>()));
    }

    @Override
    public int updateDetail(BrandEntity newBrand) {
        this.updateById(newBrand);
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(newBrand.getName());
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", newBrand.getBrandId());
        return categoryBrandRelationDao.update(categoryBrandRelationEntity, queryWrapper);
    }
}
