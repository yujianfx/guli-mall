package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.AttrGroupDao;
import cloud.stackexplode.gulimall.product.entity.AttrGroupEntity;
import cloud.stackexplode.gulimall.product.service.AttrGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity>
        implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page =
                this.page(
                        new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<AttrGroupEntity>());

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long id) {
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.and(
                    (qw) -> {
                        qw.eq("attr_group_id", key).or().like("attr_group_name", key);
                    });
        }
        if (id == 0) {
            IPage<AttrGroupEntity> page =
                    this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
            return new PageUtils(page);
        } else {
            queryWrapper.eq("catelog_id", id);
            IPage<AttrGroupEntity> page =
                    this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
            return new PageUtils(page);
        }
    }
}
