package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.SpuImagesDao;
import top.wangudiercai.gulimall.product.entity.SpuImagesEntity;
import top.wangudiercai.gulimall.product.service.SpuImagesService;

@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity>
    implements SpuImagesService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuImagesEntity> page =
        this.page(
            new Query<SpuImagesEntity>().getPage(params), new QueryWrapper<SpuImagesEntity>());

    return new PageUtils(page);
  }
}
