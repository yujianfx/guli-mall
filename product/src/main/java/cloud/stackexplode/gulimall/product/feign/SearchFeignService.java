package cloud.stackexplode.gulimall.product.feign;

import cloud.stackexplode.gulimall.common.to.es.SkuEsTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("search-service")
public interface SearchFeignService {
  @PostMapping("/search/save/product")
  R productStatusUp(List<SkuEsTo> skuEsModels);
}
