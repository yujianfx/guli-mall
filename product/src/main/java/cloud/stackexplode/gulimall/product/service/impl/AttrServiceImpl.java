package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.AttrAttrgroupRelationDao;
import cloud.stackexplode.gulimall.product.dao.AttrDao;
import cloud.stackexplode.gulimall.product.entity.AttrAttrgroupRelationEntity;
import cloud.stackexplode.gulimall.product.entity.AttrEntity;
import cloud.stackexplode.gulimall.product.service.AttrGroupService;
import cloud.stackexplode.gulimall.product.service.AttrService;
import cloud.stackexplode.gulimall.product.service.CategoryService;
import cloud.stackexplode.gulimall.product.vo.AttrRespVo;
import cloud.stackexplode.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrGroupService attrGroupService;

    @Override
    public PageUtils queryPageByCid(Map<String, Object> params, Long cid) {
        String key = (String) params.get("key");
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("search_type", 1).eq("enable", 1).eq("show_status", 0);
        if (cid != 0) {
            queryWrapper.eq("catelog_id", cid);
        }
        return StringUtils.isEmpty(key)
                ? new PageUtils(this.page(new Query<AttrEntity>().getPage(params), queryWrapper))
                : new PageUtils(
                this.page(
                        new Query<AttrEntity>().getPage(params),
                        queryWrapper.like("attr_name", key).or().like("value_select", key)));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page =
                this.page(new Query<AttrEntity>().getPage(params), new QueryWrapper<AttrEntity>());

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        AttrAttrgroupRelationEntity attrGroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrGroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        attrGroupRelationEntity.setAttrId(attrVo.getAttrId());
        attrAttrgroupRelationDao.insert(attrGroupRelationEntity);
        return this.save(attrEntity);
    }

    @Override
    public AttrRespVo getAttrDetailById(Long attrId) {
        AttrEntity attrEntity = baseMapper.selectById(attrId);
        AttrRespVo attrRespVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity, attrRespVo);
        attrRespVo.setCatelogPath(categoryService.findCatelogPath(attrEntity.getCatelogId()));
        attrRespVo.setCatelogName(categoryService.getById(attrEntity.getCatelogId()).getName());
        attrRespVo.setGroupName(
                attrGroupService
                        .getById(
                                attrAttrgroupRelationDao
                                        .selectOne(
                                                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId))
                                        .getAttrGroupId())
                        .getAttrGroupName());
        return attrRespVo;
    }
}
