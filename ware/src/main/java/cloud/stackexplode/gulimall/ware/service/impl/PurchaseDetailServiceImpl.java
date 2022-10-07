package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.PurchaseDetailDao;
import cloud.stackexplode.gulimall.ware.entity.PurchaseDetailEntity;
import cloud.stackexplode.gulimall.ware.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity>
    implements PurchaseDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String key = (String) params.get("key");
    String status = (String) params.get("status");
    String wareId = (String) params.get("wareId");
    QueryWrapper<PurchaseDetailEntity> purchaseDetailEntityQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(wareId)) {
      purchaseDetailEntityQueryWrapper.eq("ware_id", wareId);
    }
    if (StringUtils.isNotEmpty(status)) {
      purchaseDetailEntityQueryWrapper.eq("status", status);
    }
    if (StringUtils.isNotEmpty(key)) {
      purchaseDetailEntityQueryWrapper.like("sku_name", key);
    }
    IPage<PurchaseDetailEntity> page =
        this.page(
            new Query<PurchaseDetailEntity>().getPage(params), purchaseDetailEntityQueryWrapper);
    return new PageUtils(page);
  }
}
