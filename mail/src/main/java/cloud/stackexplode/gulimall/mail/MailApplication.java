package cloud.stackexplode.gulimall.mail;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

}
