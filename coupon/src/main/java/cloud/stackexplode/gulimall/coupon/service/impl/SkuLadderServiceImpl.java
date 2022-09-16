package cloud.stackexplode.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.coupon.dao.SkuLadderDao;
import cloud.stackexplode.gulimall.coupon.entity.SkuLadderEntity;
import cloud.stackexplode.gulimall.coupon.service.SkuLadderService;

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
