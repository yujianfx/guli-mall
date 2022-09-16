package cloud.stackexplode.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cloud.stackexplode.gulimall.coupon.feign")
@SpringBootApplication
public class CouponApplication {

  public static void main(String[] args) {
    SpringApplication.run(CouponApplication.class, args);
  }
}
