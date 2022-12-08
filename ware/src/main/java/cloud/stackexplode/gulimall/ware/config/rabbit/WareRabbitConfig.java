package cloud.stackexplode.gulimall.ware.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

@Configuration
@Slf4j
public class WareRabbitConfig {
    
    @Bean("ware.event.rollback.exchange")
    Exchange wareRollbackExchange() {
        return new TopicExchange("ware.event.rollback.exchange", true, false);
    }
    @Bean("ware.event.rollback.exception.exchange")
    Exchange wareExceptionRollbackExchange() {
        return new TopicExchange("ware.event.rollback.exception.exchange", true, false);
    }
    @Bean("ware.event.rollback.orderclose.exchange")
    Exchange wareOrderCloseRollbackExchange(){
        return new TopicExchange("ware.event.rollback.orderclose.exchange", true, false);
    }
    @Bean
    Binding eTorBing(){
        return new Binding("ware.event.rollback.exchange", Binding.DestinationType.EXCHANGE, "ware.event.exchange", "ware.event.rollback.#", new HashMap<>());
    }
    @Bean
    Binding rToeBinding() {
        return new Binding("ware.event.rollback.exception.exchange", Binding.DestinationType.EXCHANGE, "ware.event.rollback.exchange", "ware.event.rollback.exception.#", new HashMap<>());
    }
    @Bean
    Binding rTooBing(){
        return new Binding("ware.event.rollback.orderclose.exchange", Binding.DestinationType.EXCHANGE, "ware.event.rollback.exchange", "ware.event.rollback.orderclose.#", new HashMap<>());
    }
    @Bean("ware.event.delay.queue")
    Queue wareDelayQueue() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "ware.event.exchange");
        arguments.put("x-dead-letter-routing-key", "ware.event.rollback.exception.waresystemexception");
        arguments.put("x-message-ttl", 6000);
        return new Queue("ware.event.delay.queue", true, false, false, arguments);
    }
    @Bean("ware-delay-binding")
    Binding wareDelayBinding() {
        return new Binding("ware.event.delay.queue", Binding.DestinationType.QUEUE, "ware.event.exchange", "ware.event.delay.*", null);
    }
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
            log.info("发送消息触发confirmCallback回调" +
                    "\ncorrelationData ===> " + correlationData +
                    "\nack ===> " + ack + "" +
                    "\ncause ===> " + cause);
            log.info("=================================================");
        });

        rabbitTemplate.setReturnsCallback((message) -> {
            log.warn("消息未到达队列触发returnCallback回调" +
                    "\nmessage ===> " + message);
            // TODO 修改mq_message，设置消息状态为2-错误抵达【后期定时器重发消息】
        });
    }
}
