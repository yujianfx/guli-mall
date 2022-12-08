package cloud.stackexplode.gulimall.payment.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@EnableConfigurationProperties(AlipayProperties.class)
@Configuration
public class AlipayConfig {
    @Bean
    AlipayClient alipayClient(AlipayProperties alipayProperties) {
        return new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties. getAppId(), alipayProperties.getMerchantPrivateKey(), "json", alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), alipayProperties.getSignType());
    }

}
