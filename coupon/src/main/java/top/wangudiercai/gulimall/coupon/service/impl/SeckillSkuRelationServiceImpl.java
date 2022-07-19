package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SeckillSkuRelationDao;
import top.wangudiercai.gulimall.coupon.entity.SeckillSkuRelationEntity;
import top.wangudiercai.gulimall.coupon.service.SeckillSkuRelationService;

@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl
    extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity>
    implements SeckillSkuRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSkuRelationEntity> page =
        this.page(
            new Query<SeckillSkuRelationEntity>().getPage(params),
            new QueryWrapper<SeckillSkuRelationEntity>());

    return new PageUtils(page);
  }
}
