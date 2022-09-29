package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.SkuFullReductionDao;
import cloud.stackexplode.gulimall.coupon.entity.SkuFullReductionEntity;
import cloud.stackexplode.gulimall.coupon.service.SkuFullReductionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
