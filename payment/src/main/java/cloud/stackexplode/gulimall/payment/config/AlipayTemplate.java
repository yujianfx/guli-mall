package cloud.stackexplode.gulimall.payment.config;

import cloud.stackexplode.gulimall.common.vo.order.vo.PayVo;
import cloud.stackexplode.gulimall.payment.controller.AlipayController;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class AlipayTemplate {
    @Autowired
    private AlipayProperties alipayProperties;
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private ObjectMapper objectMapper;

    @Data
    @Accessors(chain = true)
    static class BizContent {
        @JsonProperty("out_trade_no")
        private String outTradeNo;
        @JsonProperty("total_amount")
        private String totalAmount;
        private String subject;
        private String body;
        @JsonProperty("timeout_expire")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime timeExpire;
        @JsonProperty("product_code")
        private String productCode;
    }

    public String pay(PayVo payVo) {
        String body = null;
        try {
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest
                    .setBizContent(objectMapper
                            .writeValueAsString(new BizContent()
                                    .setOutTradeNo(payVo.getOutTradeNo().toString())
                                    .setTotalAmount(payVo.getTotalAmount())
                                    .setSubject(payVo.getSubject())
                                    .setBody(payVo.getBody())
                                    .setTimeExpire(LocalDateTime.now().plusMinutes(15))
                                    .setProductCode("FAST_INSTANT_TRADE_PAY")));
            alipayRequest.setReturnUrl(alipayProperties.getReturnUrl());
            alipayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());

            body = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
