package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SkuInfoDao;
import cloud.stackexplode.gulimall.product.entity.SkuInfoEntity;
import cloud.stackexplode.gulimall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity>
    implements SkuInfoService {

  @Override
  public PageUtils queryPage(@NotNull Map<String, Object> params) {
    QueryWrapper<SkuInfoEntity> spuInfoEntityQueryWrapper = new QueryWrapper<>();
    String key = (String) params.get("key");
    String catelogId = (String) params.get("catelogId");
    String brandId = (String) params.get("brandId");
    if (StringUtils.isNotEmpty(key)) {
      spuInfoEntityQueryWrapper
          .like("sku_name", key)
          .or()
          .like("sku_title", key)
          .or()
          .like("sku_subtitle", key);
    }
    if (StringUtils.isNotEmpty(catelogId)) {
      long cid = Long.parseLong(catelogId);
      if (cid > 0) {
        spuInfoEntityQueryWrapper.eq("catelog_id", cid);
      }
    }
    if (StringUtils.isNotEmpty(brandId)) {
      long bid = Long.parseLong(brandId);
      if (bid > 0) {
        spuInfoEntityQueryWrapper.eq("brand_id", bid);
      }
    }
    String min = (String) params.get("min");
    String max = (String) params.get("max");
    if (StringUtils.isEmpty(min)) {
      min = "0";
    }
    if (StringUtils.isEmpty(max)) {
      max = "999999999";
    }
    log.debug(min+":"+max);
    spuInfoEntityQueryWrapper.between("price", min, max);
    IPage<SkuInfoEntity> page =
        this.page(new Query<SkuInfoEntity>().getPage(params), spuInfoEntityQueryWrapper);
    return new PageUtils(page);
  }
}
