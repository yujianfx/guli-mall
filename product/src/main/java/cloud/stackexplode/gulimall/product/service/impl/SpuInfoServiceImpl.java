package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SpuInfoDao;
import cloud.stackexplode.gulimall.product.entity.SpuInfoEntity;
import cloud.stackexplode.gulimall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity>
        implements SpuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page =
                this.page(new Query<SpuInfoEntity>().getPage(params), new QueryWrapper<SpuInfoEntity>());

        return new PageUtils(page);
    }
}
