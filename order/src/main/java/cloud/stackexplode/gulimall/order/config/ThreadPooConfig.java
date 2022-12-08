package cloud.stackexplode.gulimall.order.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(TaskExecutionProperties.class)
public class ThreadPooConfig {
    @Bean
    public ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean(TaskExecutionProperties taskExecutionProperties) {
        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setCorePoolSize(taskExecutionProperties.getPool().getCoreSize());
        threadPoolExecutorFactoryBean.setMaxPoolSize(taskExecutionProperties.getPool().getMaxSize());
        threadPoolExecutorFactoryBean.setKeepAliveSeconds((int) taskExecutionProperties.getPool().getKeepAlive().getSeconds());
        threadPoolExecutorFactoryBean.setQueueCapacity(taskExecutionProperties.getPool().getQueueCapacity());
        threadPoolExecutorFactoryBean.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutorFactoryBean.setThreadNamePrefix(taskExecutionProperties.getThreadNamePrefix());
        return threadPoolExecutorFactoryBean;

    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(TaskExecutionProperties taskExecutionProperties, ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean) {
        return new ThreadPoolExecutor(
                taskExecutionProperties.getPool().getCoreSize(),
                taskExecutionProperties.getPool().getMaxSize(),
                taskExecutionProperties.getPool().getKeepAlive().getSeconds(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000), threadPoolExecutorFactoryBean
        );
    }
}

