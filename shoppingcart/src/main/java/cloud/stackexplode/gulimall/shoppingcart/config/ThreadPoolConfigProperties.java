package cloud.stackexplode.gulimall.shoppingcart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.thread")
@Data
public class ThreadPoolConfigProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;

}
