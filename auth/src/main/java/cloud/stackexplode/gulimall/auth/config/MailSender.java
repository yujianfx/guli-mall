package cloud.stackexplode.gulimall.auth.config;

import cloud.stackexplode.gulimall.auth.feign.MailAuthCodeFeignService;
import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.auth.AuthCodeSender;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MailSender implements AuthCodeSender {
    @Autowired
    private MailAuthCodeFeignService mailAuthCodeFeignService;

    @Override
    public AuthCodeType support() {
        return AuthCodeType.EMAIL;
    }

    @Override
    public void send(String target, String code) {
        mailAuthCodeFeignService.sendMail(new AuthCodeRequest()
                .setTarget(target)
                .setRequestTime(LocalDateTime.now()).setCode(code));
    }
}
