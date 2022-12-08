package cloud.stackexplode.gulimall.order.feign;


import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@FeignClient("shoppingcart-service")
public interface CartFeignService {
    @RequestMapping("/shoppingcart/getCheckedItems")
    R<List<OrderItemVo>> getCheckedItems();
}
