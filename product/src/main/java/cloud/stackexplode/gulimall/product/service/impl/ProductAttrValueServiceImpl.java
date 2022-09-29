package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.ProductAttrValueDao;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.service.ProductAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("productAttrValueService")
public class ProductAttrValueServiceImpl
        extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity>
        implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page =
                this.page(
                        new Query<ProductAttrValueEntity>().getPage(params),
                        new QueryWrapper<ProductAttrValueEntity>());

        return new PageUtils(page);
    }
}
