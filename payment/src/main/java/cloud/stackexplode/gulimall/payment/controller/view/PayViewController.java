package cloud.stackexplode.gulimall.payment.controller.view;

import cloud.stackexplode.gulimall.common.to.order.OrderInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.payment.entities.AliPayTradeRecord;
import cloud.stackexplode.gulimall.payment.feign.OrderFeignService;
import cloud.stackexplode.gulimall.payment.service.AlipayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/payment/view")
public class PayViewController {
    @Qualifier("cloud.stackexplode.gulimall.payment.feign.OrderFeignService")
    @Autowired
    private OrderFeignService orderFeignService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private ObjectMapper objectMapper;
    @RequestMapping("/payView/{orderSn}")
    public String pay(@PathVariable("orderSn") Long orderSn, Model model) {
        R<OrderInfoTo> r = orderFeignService.getOrderInfo(orderSn);
        log.info("r:{}", r);
        OrderInfoTo order = r.getData();
        log.info("order:{}", order);
        model.addAttribute("order",order);
        return "pay";
    }


    @RequestMapping("/paySuccessView")
    public String paysuccess(@RequestParam Map<String, Object> aliPayNotifyParamsMap) {
        log.info("aliPayNotifyParamsMap:{}", aliPayNotifyParamsMap);
        AliPayTradeRecord aliPayTradeRecord = objectMapper.convertValue(aliPayNotifyParamsMap, AliPayTradeRecord.class);
        log.info("aliPayTradeRecord:{}", aliPayTradeRecord);
        alipayService.orderTradeSuccessNotify(aliPayTradeRecord);
        return "paysuccess";
    }
}
