package cloud.stackexplode.gulimall.oss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);

    }
}
