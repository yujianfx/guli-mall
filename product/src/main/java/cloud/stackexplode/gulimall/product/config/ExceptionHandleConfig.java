package cloud.stackexplode.gulimall.product.config;

import cloud.stackexplode.gulimall.common.exception.DefaultGlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandleConfig {
  @Bean
  @ConditionalOnMissingBean(name = "globalExceptionHandler")
  DefaultGlobalExceptionHandler globalExceptionHandler() {
    return new DefaultGlobalExceptionHandler();
  }
}
