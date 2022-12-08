package cloud.stackexplode.gulimall.ware.listener;

import cloud.stackexplode.gulimall.common.enums.order.OrderStatus;
import cloud.stackexplode.gulimall.common.to.mail.EmailWarnTo;
import cloud.stackexplode.gulimall.common.to.order.OrderTo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.ware.feign.OrderFeignService;
import cloud.stackexplode.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class WareEventListener {
    @Autowired
    private OrderFeignService orderFeignService;
    @Autowired
    private WareSkuService wareSkuService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "ware.event.rollback.orderclose.queue", durable = "true"),
                    exchange = @Exchange(name = "ware.event.rollback.orderclose.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
                    key = {"ware.event.rollback.orderclose.#"}),  ackMode = "#{T(org.springframework.amqp.core.AcknowledgeMode).MANUAL}")
    public void rollbackOfOrderClose(@Payload Long orderSn, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        try {
            log.info("收到订单关闭的消息，准备解锁库存：{}", orderSn);
            R<OrderTo> orderTo = orderFeignService.getOrderTo(orderSn);
            OrderTo order = orderTo.getData();
            if (order != null && order.getStatus().equals(OrderStatus.CREATE_NEW)) {
                log.info("订单不存在或未支付，准备解锁库存{}", order);
                wareSkuService.unlock(orderSn);
                channel.basicAck(tag, false);
            } else {
                log.warn("订单状态查询异常{}", order);
                EmailWarnTo emailWarnTo = new EmailWarnTo();
                emailWarnTo.setTarget("2653084650@qq.com");
                emailWarnTo.setSubject("订单关闭，库存解锁失败");
                emailWarnTo.setContent("订单号：" + orderSn);

                log.warn("发送邮件通知：{}", emailWarnTo);
                rabbitTemplate.convertAndSend("email.exchange", "email.system.warn.order", emailWarnTo);

                channel.basicAck(tag, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(tag, false, true);
        }
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "ware.event.rollback.exception.queue", durable = "true"),
                    exchange = @Exchange(name = "ware.event.rollback.exception.exchange",
                            type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
                    key = {"ware.event.rollback.exception.#"}),  ackMode = "#{T(org.springframework.amqp.core.AcknowledgeMode).MANUAL}")
    @RabbitHandler
    public void rollbackOfSelf(@Payload Long taskId, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        try {
            log.info("准备解锁库存");
            wareSkuService.unlock(taskId);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(tag, false, true);
        }
    }

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "ware.event.commit.queue", durable = "true"),
                    exchange = @Exchange(value = "ware.event.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
                    key = "ware.event.commit.*"
            ),  ackMode = "#{T(org.springframework.amqp.core.AcknowledgeMode).MANUAL}")
    public void commit(@Payload Long orderSn, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        try {
            log.info("收到订单支付成功的消息：{}", orderSn);
            wareSkuService.commit(orderSn);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(tag, false, true);
        }
    }
}


