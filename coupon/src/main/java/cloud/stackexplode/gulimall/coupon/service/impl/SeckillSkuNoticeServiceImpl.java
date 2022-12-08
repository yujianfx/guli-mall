package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.SeckillSkuNoticeDao;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.SeckillSkuNoticeEntity;
import cloud.stackexplode.gulimall.coupon.service.SeckillSkuNoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
