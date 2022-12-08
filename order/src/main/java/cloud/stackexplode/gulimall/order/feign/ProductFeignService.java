package cloud.stackexplode.gulimall.order.feign;

import cloud.stackexplode.gulimall.common.entities.product.entity.SpuInfoEntity;
import cloud.stackexplode.gulimall.common.to.product.SpuInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("product-service")
@ResponseBody
public interface ProductFeignService {
    @GetMapping("product/spuinfo/getSpuBySkuId/{skuId}")
    R<SpuInfoTo> getSpuBySkuId(@PathVariable("skuId") Long skuId);

    @RequestMapping("product/skuinfo/info/{skuId}")
    R<SpuInfoEntity> info(@PathVariable("skuId") Long skuId);
}
