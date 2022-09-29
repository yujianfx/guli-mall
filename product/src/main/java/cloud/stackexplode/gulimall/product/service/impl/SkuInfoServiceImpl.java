package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SkuInfoDao;
import cloud.stackexplode.gulimall.product.entity.SkuInfoEntity;
import cloud.stackexplode.gulimall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity>
        implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page =
                this.page(new Query<SkuInfoEntity>().getPage(params), new QueryWrapper<SkuInfoEntity>());

        return new PageUtils(page);
    }
}
