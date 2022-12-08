package cloud.stackexplode.gulimall.order.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

/**
 * @author 26530
 */
@Slf4j
@Configuration
@EnableRabbit
public class OrderRabbitConfig {
    @Primary
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        initRabbitTemplate(rabbitTemplate);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public void initRabbitTemplate(RabbitTemplate rabbitTemplate) {

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            } else {
                log.error("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message({})", returned.getExchange(), returned.getRoutingKey(), returned.getReplyCode(), returned.getReplyText(), returned.getMessage());
        });
    }

    @Bean("order.event.delay.queue")
    Queue orderDelayQueue() {
        System.out.println();
        HashMap<String, Object> arguments = new HashMap<>(4);
        arguments.put("x-dead-letter-exchange", "order.event.exchange");
        arguments.put("x-dead-letter-routing-key", "order.event.close.timeout");
        arguments.put("x-message-ttl", 300000);
        return new Queue("order.event.delay.queue", true, false, false, arguments);
    }

    @Bean
    Binding orderDelayBinding() {
        return new Binding("order.event.delay.queue", Binding.DestinationType.QUEUE, "order.event.exchange", "order.event.create.#", null);
    }


}
