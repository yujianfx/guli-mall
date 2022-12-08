package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.CouponHistoryDao;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.CouponHistoryEntity;
import cloud.stackexplode.gulimall.coupon.service.CouponHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity>
        implements CouponHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponHistoryEntity> page =
                this.page(
                        new Query<CouponHistoryEntity>().getPage(params),
                        new QueryWrapper<CouponHistoryEntity>());

        return new PageUtils(page);
    }
}
