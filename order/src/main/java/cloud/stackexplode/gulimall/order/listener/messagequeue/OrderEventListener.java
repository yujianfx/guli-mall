package cloud.stackexplode.gulimall.order.listener.messagequeue;

import cloud.stackexplode.gulimall.common.entities.order.entity.OrderEntity;
import cloud.stackexplode.gulimall.common.enums.order.OrderStatus;
import cloud.stackexplode.gulimall.order.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OrderEventListener {
    @Autowired
    private OrderService orderService;

    @RabbitHandler
    @SendTo("ware.event.exchange/ware.event.rollback.orderclose.ordertimeouttopay")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "order.event.close.queue", durable = "true"),
            exchange = @Exchange(name = "order.event.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = {"order.event.close.*"}),
            ackMode = "#{T(org.springframework.amqp.core.AcknowledgeMode).MANUAL}")
    public Message<String> listenerOrderClose(@Payload Long orderSn,
                                              @Header(AmqpHeaders.CHANNEL) Channel channel,
                                              @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        try {
            log.info("收到过期的订单信息，准备关闭订单：{}", orderSn);
            orderService.closeOrder(orderSn);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag, false, true);
        }
        return new GenericMessage<>(orderSn);
    }

    @RabbitHandler
    @SendTo({"ware.event.exchange/ware.event.commit.orderpayed"})
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "order.event.payed.queue", durable = "true"),
            exchange = @Exchange(name = "order.event.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "order.event.payed.*"),
            ackMode = "#{T(org.springframework.amqp.core.AcknowledgeMode).MANUAL}")
    public Message<String> listenOrderTrade(@Payload Long orderSn,
                                            @Header(AmqpHeaders.CHANNEL) Channel channel,
                                            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        try {
            orderService.lambdaUpdate().eq(OrderEntity::getOrderSn, orderSn).set(OrderEntity::getStatus, OrderStatus.PAYED).update();
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag, false, true);
        }
        return new GenericMessage<>(orderSn);
    }
}
