package cloud.stackexplode.gulimall.ware.feign;

import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface SkuFeignService {
  @GetMapping("/product/skuinfo/info/{skuId}")
  R getSkuInfo(@PathVariable("skuId") Long skuId);
}
