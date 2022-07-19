package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SeckillSkuNoticeDao;
import top.wangudiercai.gulimall.coupon.entity.SeckillSkuNoticeEntity;
import top.wangudiercai.gulimall.coupon.service.SeckillSkuNoticeService;

@Service("seckillSkuNoticeService")
public class SeckillSkuNoticeServiceImpl
    extends ServiceImpl<SeckillSkuNoticeDao, SeckillSkuNoticeEntity>
    implements SeckillSkuNoticeService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSkuNoticeEntity> page =
        this.page(
            new Query<SeckillSkuNoticeEntity>().getPage(params),
            new QueryWrapper<SeckillSkuNoticeEntity>());

    return new PageUtils(page);
  }
}
