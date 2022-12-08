package cloud.stackexplode.gulimall.auth.feign;

import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.auth.AuthCodeSender;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mail-service")
public interface MailAuthCodeFeignService {
    @PostMapping("/mail/sendMail")
    R sendMail(@RequestBody AuthCodeRequest authCodeRequest);
}
