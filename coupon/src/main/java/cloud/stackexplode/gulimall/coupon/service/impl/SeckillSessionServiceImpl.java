package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.SeckillSessionDao;
import cloud.stackexplode.gulimall.coupon.entity.SeckillSessionEntity;
import cloud.stackexplode.gulimall.coupon.service.SeckillSessionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
