package cloud.stackexplode.gulimall.payment.service;

import cloud.stackexplode.gulimall.common.enums.order.PaymentMethodEnum;
import cloud.stackexplode.gulimall.payment.entities.AliPayTradeRecord;

public interface AlipayService {
    String pay(Long orderSn, PaymentMethodEnum paymentMethodEnum);

    void orderTradeSuccessNotify(AliPayTradeRecord aliPayTradeRecord);
}
