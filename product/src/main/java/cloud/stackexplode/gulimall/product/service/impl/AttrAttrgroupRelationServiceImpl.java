package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.AttrAttrgroupRelationDao;
import cloud.stackexplode.gulimall.common.entities.product.entity.AttrAttrgroupRelationEntity;
import cloud.stackexplode.gulimall.product.service.AttrAttrgroupRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl
        extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity>
        implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page =
                this.page(
                        new Query<AttrAttrgroupRelationEntity>().getPage(params),
                        new QueryWrapper<AttrAttrgroupRelationEntity>());

        return new PageUtils(page);
    }
}
