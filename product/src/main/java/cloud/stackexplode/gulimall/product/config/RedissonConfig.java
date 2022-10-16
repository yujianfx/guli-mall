package cloud.stackexplode.gulimall.product.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedissonConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private String port;
  @Bean
  public RedissonClient redisson() {
    Config config = new Config();
    config.setCodec(new JsonJacksonCodec())
        .useSingleServer()
        .setAddress("redis://" + host + ":" + port)
        .setTimeout(5000)
        .setConnectionPoolSize(50)
        .setConnectionMinimumIdleSize(25)
        .setDatabase(0);
    return Redisson.create(config);
  }
}
