package cloud.stackexplode.gulimall.order.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayWebController {

//    @Autowired
//    private AlipayTemplate alipayTemplate;
//
//    @Autowired
//    private OrderService orderService;

//    @ResponseBody
//    @GetMapping(value = "/aliPayOrder",produces = "text/html")
//    public String aliPayOrder(@RequestParam("orderSn") Long orderSn) throws AlipayApiException {
//        System.out.println("接收到订单信息orderSn："+orderSn);
//        PayVo payVo = orderService.getOrderPay(orderSn);
//        String pay = alipayTemplate.pay(payVo);
//        return pay;
//    }


}
