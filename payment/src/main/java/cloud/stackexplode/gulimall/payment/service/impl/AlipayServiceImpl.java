package cloud.stackexplode.gulimall.payment.service.impl;

import cloud.stackexplode.gulimall.common.components.IdWorker;
import cloud.stackexplode.gulimall.common.enums.order.PaymentMethodEnum;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.PayVo;
import cloud.stackexplode.gulimall.payment.config.AlipayTemplate;
import cloud.stackexplode.gulimall.payment.dao.AliPayTradeRecordDao;
import cloud.stackexplode.gulimall.payment.entities.AliPayTradeRecord;
import cloud.stackexplode.gulimall.payment.enums.MessageStatus;
import cloud.stackexplode.gulimall.payment.feign.OrderFeignService;
import cloud.stackexplode.gulimall.payment.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private AliPayTradeRecordDao aliPayTradeRecordDao;
    @Qualifier("cloud.stackexplode.gulimall.payment.feign.OrderFeignService")
    @Autowired
    private OrderFeignService orderFeignService;
    @Autowired
    private AlipayTemplate alipayTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;

    @Override
    public String pay(Long orderSn, PaymentMethodEnum paymentMethodEnum) {
        R<PayVo> r = orderFeignService.getOrderPay(orderSn);
        PayVo payVo = r.getData();
        return alipayTemplate.pay(payVo);
    }

    @Override
    public void orderTradeSuccessNotify(AliPayTradeRecord aliPayTradeRecord) {
        aliPayTradeRecord.setMessageStatus(MessageStatus.UNPROCESSED);
        aliPayTradeRecord.setId(idWorker.nextId());
        if (!aliPayTradeRecordDao.existsAliPayTradeRecordByOutTradeNo(aliPayTradeRecord.getOutTradeNo())) {
            aliPayTradeRecordDao.save(aliPayTradeRecord);
            rabbitTemplate.convertAndSend("order-event-exchange", "order.event.payed.alipayed", aliPayTradeRecord.getOutTradeNo());
        }
    }
}
