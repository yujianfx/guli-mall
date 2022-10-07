package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.WareSkuDao;
import cloud.stackexplode.gulimall.ware.entity.WareSkuEntity;
import cloud.stackexplode.gulimall.ware.service.WareSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity>
    implements WareSkuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String skuName = (String) params.get("skuName");
    String wareId = (String) params.get("wareId");
    QueryWrapper<WareSkuEntity> wareInfoEntityQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(skuName)) {
      wareInfoEntityQueryWrapper.like("sku_name", skuName);
    }
    if (StringUtils.isNotEmpty(wareId)) {
      wareInfoEntityQueryWrapper.eq("ware_id", wareId);
    }
    IPage<WareSkuEntity> page =
        this.page(new Query<WareSkuEntity>().getPage(params), wareInfoEntityQueryWrapper);
    return new PageUtils(page);
  }

  @Override
  public WareSkuEntity getBySkuId(Long id) {
    return baseMapper.selectOne(new QueryWrapper<WareSkuEntity>().eq("sku_id", id));
  }
}
