package cloud.stackexplode.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.ware.dao.WareSkuDao;
import cloud.stackexplode.gulimall.ware.entity.WareSkuEntity;
import cloud.stackexplode.gulimall.ware.service.WareSkuService;

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity>
    implements WareSkuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareSkuEntity> page =
        this.page(new Query<WareSkuEntity>().getPage(params), new QueryWrapper<WareSkuEntity>());

    return new PageUtils(page);
  }
}
