package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.BrandDao;
import top.wangudiercai.gulimall.product.entity.BrandEntity;
import top.wangudiercai.gulimall.product.service.BrandService;

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<BrandEntity> page =
        this.page(new Query<BrandEntity>().getPage(params), new QueryWrapper<BrandEntity>());

    return new PageUtils(page);
  }
}
