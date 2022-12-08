package cloud.stackexplode.gulimall.payment.feign;

import cloud.stackexplode.gulimall.common.to.order.OrderInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.PayVo;
import cloud.stackexplode.gulimall.payment.feign.fallback.OrderFeignServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@FeignClient(value = "order-service", fallback = OrderFeignServiceFallbackImpl.class)
public interface OrderFeignService {
    @GetMapping("/order/order/getOrderPay/{orderSn}")
    R<PayVo> getOrderPay(@PathVariable("orderSn") Long orderSn);

    @GetMapping("/order/order/getInfoByOrderSn/{orderSn}")
    R<OrderInfoTo> getOrderInfo(@PathVariable("orderSn") Long orderSn);
}
