package cloud.stackexplode.gulimall.product.feign;

import cloud.stackexplode.gulimall.common.to.ware.WareSkuTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 器皿装服务
 *
 * @author 26530
 * @date 2022/10/08
 */@FeignClient("ware-service")
public interface WareFeignService {
  /**
   * 得到sku股票
   *
   * @param skuIds sku id
   * @return {@link R}
   */@PostMapping("/ware/waresku/infoListSukId/")
  R getSkuHasStocks(@RequestBody List<Long> skuIds);

  /**
   * 保存sku器皿
   *
   * @param wareSkuTos 器皿sku服务条款
   * @return {@link R}
   */@PostMapping("/ware/waresku/saveSkuWare")
  R saveSkuWare(@RequestBody List<WareSkuTo> wareSkuTos);
}
