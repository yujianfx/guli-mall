package cloud.stackexplode.gulimall.auth.config;

import cloud.stackexplode.gulimall.auth.feign.SmsAuthCodeFeignService;
import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.auth.AuthCodeSender;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SmsSender implements AuthCodeSender {
    @Autowired
    private SmsAuthCodeFeignService smsAuthCodeFeignService;

    @Override
    public AuthCodeType support() {
        return AuthCodeType.SMS;
    }

    @Override
    public void send(String target, String code) {
        smsAuthCodeFeignService.sendSms(new AuthCodeRequest()
                .setTarget(target)
                .setRequestTime(LocalDateTime.now()));
    }
}

