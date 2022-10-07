package cloud.stackexplode.gulimall.ware;

import cloud.stackexplode.gulimall.ware.config.EnableDefaultGlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDefaultGlobalExceptionHandler
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cloud.stackexplode.gulimall.ware.feign")
@SpringBootApplication
@MapperScan(basePackages = "cloud.stackexplode.gulimall.ware.dao")
public class WareApplication {
  public static void main(String[] args) {
    SpringApplication.run(WareApplication.class, args);
  }
}
