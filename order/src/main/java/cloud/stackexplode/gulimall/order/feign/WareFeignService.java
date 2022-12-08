package cloud.stackexplode.gulimall.order.feign;


import cloud.stackexplode.gulimall.common.to.product.SkuHasStockVo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.FareVo;
import cloud.stackexplode.gulimall.common.vo.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("ware-service")
@ResponseBody
public interface WareFeignService {

    @PostMapping("ware/waresku/getSkuHasStocks")
    R<List<SkuHasStockVo>> getSkuHasStocks(@RequestBody List<Long> ids);

    @GetMapping("ware/wareinfo/fare")
    R<FareVo> getFare(@RequestParam("addrId") Long addrId, @RequestParam("wareId") Long wareId);

    @PostMapping("ware/waresku/lockWare")
    R orderLockStock(@RequestBody WareSkuLockVo itemVos);
}
