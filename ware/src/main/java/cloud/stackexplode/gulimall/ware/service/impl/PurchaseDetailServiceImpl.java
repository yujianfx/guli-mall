package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.PurchaseDetailDao;
import cloud.stackexplode.gulimall.ware.entity.PurchaseDetailEntity;
import cloud.stackexplode.gulimall.ware.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity>
        implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseDetailEntity> page =
                this.page(
                        new Query<PurchaseDetailEntity>().getPage(params),
                        new QueryWrapper<PurchaseDetailEntity>());

        return new PageUtils(page);
    }
}
