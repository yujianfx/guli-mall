package cloud.stackexplode.gulimall.payment.controller;

import cloud.stackexplode.gulimall.common.enums.order.PaymentMethodEnum;
import cloud.stackexplode.gulimall.payment.entities.AliPayTradeRecord;
import cloud.stackexplode.gulimall.payment.service.AlipayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/payment/ali")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private ObjectMapper objectMapper;
    @ResponseBody
    @GetMapping(value = "/pay/{orderSn}/{payMethod}", produces = "text/html")
    public String pay(@PathVariable("orderSn") Long orderSn, @PathVariable("payMethod") PaymentMethodEnum payMethod) {
        return alipayService.pay(orderSn, payMethod);
    }

    @PostMapping(value = "/notify", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void notify(@RequestParam Map<String, Object> aliPayNotifyParamsMap) {
        log.info("aliPayNotifyParamsMap:{}", aliPayNotifyParamsMap);
        AliPayTradeRecord aliPayTradeRecord = objectMapper.convertValue(aliPayNotifyParamsMap, AliPayTradeRecord.class);
        log.info("aliPayTradeRecord:{}", aliPayTradeRecord);
        alipayService.orderTradeSuccessNotify(aliPayTradeRecord);
    }
}
