package cloud.stackexplode.gulimall.ware.feign;

import cloud.stackexplode.gulimall.common.to.order.OrderTo;
import cloud.stackexplode.gulimall.common.to.order.OrderWareResetTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("order-service")
@ResponseBody
public interface OrderFeignService {
    @GetMapping("/order/order/detailInfo/{orderSn}")
    R<OrderWareResetTo> getOrderWareResetTo(@PathVariable("orderSn") Long orderSn);

    @GetMapping("/order/order/getInfoByOrderSn/{orderSn}")
    R<OrderTo> getOrderTo(@PathVariable("orderSn") Long orderSn);
}
