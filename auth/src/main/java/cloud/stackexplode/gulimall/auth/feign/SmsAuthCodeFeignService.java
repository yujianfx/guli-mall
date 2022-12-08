package cloud.stackexplode.gulimall.auth.feign;

import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("sms-service")
public interface SmsAuthCodeFeignService {
    @PostMapping("/sms/sendSms")
    R sendSms(AuthCodeRequest authCodeRequest);
}
