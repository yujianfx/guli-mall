package top.wangudiercai.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.ware.dao.WareInfoDao;
import top.wangudiercai.gulimall.ware.entity.WareInfoEntity;
import top.wangudiercai.gulimall.ware.service.WareInfoService;

@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity>
    implements WareInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareInfoEntity> page =
        this.page(new Query<WareInfoEntity>().getPage(params), new QueryWrapper<WareInfoEntity>());

    return new PageUtils(page);
  }
}
