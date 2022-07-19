package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.SeckillSessionDao;
import top.wangudiercai.gulimall.coupon.entity.SeckillSessionEntity;
import top.wangudiercai.gulimall.coupon.service.SeckillSessionService;

@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity>
    implements SeckillSessionService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSessionEntity> page =
        this.page(
            new Query<SeckillSessionEntity>().getPage(params),
            new QueryWrapper<SeckillSessionEntity>());

    return new PageUtils(page);
  }
}
