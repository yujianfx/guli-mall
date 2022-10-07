package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.AttrAttrgroupRelationDao;
import cloud.stackexplode.gulimall.product.dao.AttrDao;
import cloud.stackexplode.gulimall.product.entity.AttrAttrgroupRelationEntity;
import cloud.stackexplode.gulimall.product.entity.AttrEntity;
import cloud.stackexplode.gulimall.product.entity.AttrGroupEntity;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.service.*;
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

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
  @Autowired private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
  @Autowired private CategoryService categoryService;
  @Autowired private AttrGroupService attrGroupService;
  @Autowired private AttrAttrgroupRelationService attrAttrgroupRelationService;
  @Autowired private ProductAttrValueService productAttrValueService;

  @Override
  public List<ProductAttrValueEntity> queryPageBySpuId(Long sId) {
    QueryWrapper<ProductAttrValueEntity> queryWrapper =
        new QueryWrapper<ProductAttrValueEntity>().eq(sId > 0, "spu_id", sId);
    return productAttrValueService.list(queryWrapper);
  }

  @Override
  public PageUtils queryPageByCid(Map<String, Object> params, Integer attrType, Long cid) {
    String key = (String) params.get("key");
    QueryWrapper<AttrEntity> queryWrapper =
        new QueryWrapper<AttrEntity>()
            .eq("enable", 1)
            .eq("show_status", 0)
            .eq("attr_type", attrType);
    if (cid != 0) {
      queryWrapper.eq("catelog_id", cid);
    }
    if (StringUtils.isNotEmpty(key)) {
      IPage<AttrEntity> keySearchPage =
          this.page(
              new Query<AttrEntity>().getPage(params),
              queryWrapper.like("attr_name", key).or().like("value_select", key));
      PageUtils pageUtils = new PageUtils(keySearchPage);
      return pageUtils.setList(allDetails(keySearchPage));
    } else {
      IPage<AttrEntity> iPage = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
      PageUtils pageUtils = new PageUtils(iPage);
      return pageUtils.setList(allDetails(iPage));
    }
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
    this.save(attrEntity);
    AttrAttrgroupRelationEntity attrGroupRelationEntity = new AttrAttrgroupRelationEntity();
    attrGroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
    attrGroupRelationEntity.setAttrId(attrEntity.getAttrId());

    return 0 < attrAttrgroupRelationDao.insert(attrGroupRelationEntity);
  }

  @Override
  public AttrRespVo getAttrDetailById(Integer attrType, Long attrId) {
    AttrEntity attrEntity = baseMapper.selectById(attrId);
    AttrRespVo attrRespVo = new AttrRespVo();
    BeanUtils.copyProperties(attrEntity, attrRespVo);
    attrRespVo.setCatelogPath(categoryService.findCatelogPath(attrEntity.getCatelogId()));
    attrRespVo.setCatelogName(categoryService.getById(attrEntity.getCatelogId()).getName());
    QueryWrapper<AttrAttrgroupRelationEntity> queryWrapper =
        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId);
    AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
        attrAttrgroupRelationDao.selectOne(queryWrapper);
    AttrGroupEntity attrGroupEntity =
        attrGroupService.getById(attrAttrgroupRelationEntity.getAttrGroupId());
    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
    attrRespVo.setAttrGroupId(attrGroupEntity.getAttrGroupId());
    return attrRespVo;
  }

  private List<AttrRespVo> allDetails(@NotNull IPage<AttrEntity> iPage) {
    return iPage.getRecords().stream()
        .map(
            attrEntity -> {
              Long attrId = attrEntity.getAttrId();
              Long catelogId = attrEntity.getCatelogId();
              String cname = categoryService.getById(catelogId).getName();
              AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
                  attrAttrgroupRelationService.getOne(
                      new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
              Long groupId = attrAttrgroupRelationEntity.getAttrGroupId();
              AttrGroupEntity attrGroupEntity = attrGroupService.getById(groupId);
              String agName = attrGroupEntity.getAttrGroupName();
              AttrRespVo attrRespVo = new AttrRespVo();
              BeanUtils.copyProperties(attrEntity, attrRespVo);
              attrRespVo.setGroupName(agName).setCatelogName(cname);
              return attrRespVo;
            })
        .collect(Collectors.toList());
  }
}
