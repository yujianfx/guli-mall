package cloud.stackexplode.gulimall.product.feign;

import cloud.stackexplode.gulimall.common.to.product.SkuReductionTo;
import cloud.stackexplode.gulimall.common.to.product.SpuBoundTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("coupon-service")
public interface CouponFeignService {
  @PostMapping("/coupon/spubounds/save")
  R saveBounds(@RequestBody SpuBoundTo spuBoundTo);

  @PostMapping("/coupon/skufullreduction/save")
  R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
