package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.SpuInfoDescDao;
import top.wangudiercai.gulimall.product.entity.SpuInfoDescEntity;
import top.wangudiercai.gulimall.product.service.SpuInfoDescService;

@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity>
    implements SpuInfoDescService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuInfoDescEntity> page =
        this.page(
            new Query<SpuInfoDescEntity>().getPage(params), new QueryWrapper<SpuInfoDescEntity>());

    return new PageUtils(page);
  }
}
