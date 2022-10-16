package cloud.stackexplode.gulimall.product;

import cloud.stackexplode.gulimall.product.config.EnableDefaultGlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@EnableCaching
@EnableDefaultGlobalExceptionHandler
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cloud.stackexplode.gulimall.product.feign")
@SpringBootApplication
@MapperScan("cloud.stackexplode.gulimall.product.dao")
public class ProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }
}
