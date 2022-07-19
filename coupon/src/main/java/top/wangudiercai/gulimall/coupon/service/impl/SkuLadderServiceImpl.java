package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SkuLadderDao;
import top.wangudiercai.gulimall.coupon.entity.SkuLadderEntity;
import top.wangudiercai.gulimall.coupon.service.SkuLadderService;

@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity>
    implements SkuLadderService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuLadderEntity> page =
        this.page(
            new Query<SkuLadderEntity>().getPage(params), new QueryWrapper<SkuLadderEntity>());

    return new PageUtils(page);
  }
}
