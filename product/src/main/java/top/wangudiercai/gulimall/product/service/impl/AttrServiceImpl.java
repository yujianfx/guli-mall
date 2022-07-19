package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.AttrDao;
import top.wangudiercai.gulimall.product.entity.AttrEntity;
import top.wangudiercai.gulimall.product.service.AttrService;

@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrEntity> page =
        this.page(new Query<AttrEntity>().getPage(params), new QueryWrapper<AttrEntity>());

    return new PageUtils(page);
  }
}
