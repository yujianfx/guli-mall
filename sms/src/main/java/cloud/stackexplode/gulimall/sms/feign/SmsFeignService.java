package cloud.stackexplode.gulimall.sms.feign;

import cloud.stackexplode.gulimall.sms.config.OpenFeignConfig;
import cloud.stackexplode.gulimall.sms.to.SmsRequestTo;
import cloud.stackexplode.gulimall.sms.to.SmsResponseTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author 26530
 * @date 2022/10/17
 */
@FeignClient(url = "https://service-9wijw3k1-1308991497.bj.apigw.tencentcs.com/", name = "sms", configuration = OpenFeignConfig.class)
public interface SmsFeignService {
    /**
     * 发送短信
     *
     * @param requestTo   请求
     * @param httpHeaders http头信息
     * @return {@link SmsResponseTo}
     */
    @PostMapping(value = "/release/v1/tools/sms/code/sender", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    SmsResponseTo sendSms(SmsRequestTo requestTo, @RequestHeader Map<String, String> httpHeaders);
}
