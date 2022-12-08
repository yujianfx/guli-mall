package cloud.stackexplode.gulimall.ware.feign;

import cloud.stackexplode.gulimall.common.to.product.SkuInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "product-service", contextId = "ware")
public interface ProductFeignService {
    @GetMapping("/product/skuinfo/info/{skuId}")
    R<SkuInfoTo> getSkuInfo(Long skuId);
}
