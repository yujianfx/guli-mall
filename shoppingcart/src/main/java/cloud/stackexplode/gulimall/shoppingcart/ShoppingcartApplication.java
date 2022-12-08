package cloud.stackexplode.gulimall.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients
public class ShoppingcartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);
    }

}
