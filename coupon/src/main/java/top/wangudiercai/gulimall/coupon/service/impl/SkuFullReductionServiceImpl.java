package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SkuFullReductionDao;
import top.wangudiercai.gulimall.coupon.entity.SkuFullReductionEntity;
import top.wangudiercai.gulimall.coupon.service.SkuFullReductionService;

@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl
    extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity>
    implements SkuFullReductionService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuFullReductionEntity> page =
        this.page(
            new Query<SkuFullReductionEntity>().getPage(params),
            new QueryWrapper<SkuFullReductionEntity>());

    return new PageUtils(page);
  }
}
