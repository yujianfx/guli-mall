package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SpuImagesDao;
import cloud.stackexplode.gulimall.product.entity.SpuImagesEntity;
import cloud.stackexplode.gulimall.product.service.SpuImagesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity>
        implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page =
                this.page(
                        new Query<SpuImagesEntity>().getPage(params), new QueryWrapper<SpuImagesEntity>());

        return new PageUtils(page);
    }
}
