package cloud.stackexplode.gulimall.product.config;

import cloud.stackexplode.gulimall.common.constant.global.GlobalSessionConfigConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class ProductSessionConfig {
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName(GlobalSessionConfigConstant.SESSION_ID_NAME);
        serializer.setDomainName(GlobalSessionConfigConstant.COOKIE_DOMAIN);
        return serializer;
    }

}
