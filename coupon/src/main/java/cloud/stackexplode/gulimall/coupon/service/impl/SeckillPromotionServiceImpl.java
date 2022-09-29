package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.SeckillPromotionDao;
import cloud.stackexplode.gulimall.coupon.entity.SeckillPromotionEntity;
import cloud.stackexplode.gulimall.coupon.service.SeckillPromotionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("seckillPromotionService")
public class SeckillPromotionServiceImpl
        extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity>
        implements SeckillPromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillPromotionEntity> page =
                this.page(
                        new Query<SeckillPromotionEntity>().getPage(params),
                        new QueryWrapper<SeckillPromotionEntity>());

        return new PageUtils(page);
    }
}
