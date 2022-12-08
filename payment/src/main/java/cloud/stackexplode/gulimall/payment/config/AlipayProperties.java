package cloud.stackexplode.gulimall.payment.config;


/**
 * @author 26530
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pay.alipay")
public class AlipayProperties {
    private String appId;
    private String merchantPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String returnUrl;
    private String signType = "RSA2";
    private String charset = "utf-8";
    private String gatewayUrl = "https://openapi.alipay.com/gateway.do";
}
