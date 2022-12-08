package cloud.stackexplode.gulimall.shoppingcart.feign;


import cloud.stackexplode.gulimall.common.to.product.SkuInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "product-service", contextId = "shoppingcart")
@ResponseBody
public interface ProductFeignService {
    @GetMapping("product/skuinfo/info/{skuId}")
    R<SkuInfoTo> info(@PathVariable("skuId") Long skuId);

    @GetMapping("product/skusaleattrvalue/getSkuSaleAttrValuesAsString/{skuId}")
    R<List<String>> getSkuSaleAttrValuesAsString(@PathVariable("skuId") Long skuId);
}
