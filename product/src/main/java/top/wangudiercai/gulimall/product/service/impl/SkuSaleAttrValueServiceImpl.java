package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.SkuSaleAttrValueDao;
import top.wangudiercai.gulimall.product.entity.SkuSaleAttrValueEntity;
import top.wangudiercai.gulimall.product.service.SkuSaleAttrValueService;

@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl
    extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity>
    implements SkuSaleAttrValueService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuSaleAttrValueEntity> page =
        this.page(
            new Query<SkuSaleAttrValueEntity>().getPage(params),
            new QueryWrapper<SkuSaleAttrValueEntity>());

    return new PageUtils(page);
  }
}
