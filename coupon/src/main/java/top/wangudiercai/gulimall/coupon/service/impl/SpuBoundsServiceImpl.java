package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SpuBoundsDao;
import top.wangudiercai.gulimall.coupon.entity.SpuBoundsEntity;
import top.wangudiercai.gulimall.coupon.service.SpuBoundsService;

@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity>
    implements SpuBoundsService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuBoundsEntity> page =
        this.page(
            new Query<SpuBoundsEntity>().getPage(params), new QueryWrapper<SpuBoundsEntity>());

    return new PageUtils(page);
  }
}
