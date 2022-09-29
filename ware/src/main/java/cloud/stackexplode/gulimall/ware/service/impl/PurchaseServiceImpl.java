package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.PurchaseDao;
import cloud.stackexplode.gulimall.ware.entity.PurchaseEntity;
import cloud.stackexplode.gulimall.ware.service.PurchaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity>
        implements PurchaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page =
                this.page(new Query<PurchaseEntity>().getPage(params), new QueryWrapper<PurchaseEntity>());

        return new PageUtils(page);
    }
}
