package cloud.stackexplode.gulimall.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cloud.stackexplode.gulimall.sms.feign")
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }

}
