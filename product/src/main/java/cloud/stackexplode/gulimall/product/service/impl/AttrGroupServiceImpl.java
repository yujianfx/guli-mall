package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.AttrGroupDao;
import cloud.stackexplode.gulimall.product.entity.AttrAttrgroupRelationEntity;
import cloud.stackexplode.gulimall.product.entity.AttrGroupEntity;
import cloud.stackexplode.gulimall.product.service.AttrAttrgroupRelationService;
import cloud.stackexplode.gulimall.product.service.AttrGroupService;
import cloud.stackexplode.gulimall.product.service.AttrService;
import cloud.stackexplode.gulimall.product.vo.AttrGroupWithAllAttrsVo;
import cloud.stackexplode.gulimall.product.vo.AttrRespVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * impl attr集团服务
 *
 * @author 26530
 * @date 2022/10/05
 */@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity>
    implements AttrGroupService {
  /** attr attrgroup关系服务 */@Autowired private AttrAttrgroupRelationService attrAttrgroupRelationService;
  /** attr服务 */@Autowired private AttrService attrService;

  /**
   * 查询页面
   *
   * @param params 参数个数
   * @return {@link PageUtils}
   */@Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrGroupEntity> page =
        this.page(
            new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<AttrGroupEntity>());

    return new PageUtils(page);
  }

  /**
   * 与所有attrs查询页面
   *
   * @param params 参数个数
   * @param id id
   * @return {@link PageUtils}
   */@Override
  public PageUtils queryPageWithAllAttrs(Map<String, Object> params, Long id) {
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
      PageUtils pageUtils = new PageUtils(page);
      List<AttrGroupWithAllAttrsVo> groupWithAllAttrsVos =
          page.getRecords().stream()
              .map(
                  attrGroupEntity -> {
                    AttrGroupWithAllAttrsVo attrGroupWithAllAttrsVo = new AttrGroupWithAllAttrsVo();
                    BeanUtils.copyProperties(attrGroupEntity, attrGroupWithAllAttrsVo);
                    Long attrGroupId = attrGroupEntity.getAttrGroupId();
                    List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities =
                        attrAttrgroupRelationService.list(
                            new QueryWrapper<AttrAttrgroupRelationEntity>()
                                .eq("attr_group_id", attrGroupId));
                    List<AttrRespVo> attrRespVos =
                        attrAttrgroupRelationEntities.stream()
                            .map(
                                (attrAttrgroupRelationEntity) -> {
                                  AttrRespVo attrRespVo = new AttrRespVo();
                                  BeanUtils.copyProperties(
                                      attrService.getById(attrAttrgroupRelationEntity.getAttrId()),
                                      attrRespVo);
                                  AttrGroupEntity groupEntity =
                                      this.getById(attrAttrgroupRelationEntity.getAttrGroupId());
                                  attrRespVo.setGroupName(groupEntity.getAttrGroupName());
                                  return attrRespVo;
                                })
                            .collect(Collectors.toList());
                    attrGroupWithAllAttrsVo.setAttrRespVos(attrRespVos);
                    return attrGroupWithAllAttrsVo;
                  })
              .collect(Collectors.toList());
      return pageUtils.setList(groupWithAllAttrsVos);
    }
  }

  /**
   * 查询页面
   *
   * @param params 参数个数
   * @param id id
   * @return {@link PageUtils}
   */@Override
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
