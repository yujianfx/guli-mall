package cloud.stackexplode.gulimall.auth.config;

import cloud.stackexplode.gulimall.common.auth.AuthCodeSender;
import cloud.stackexplode.gulimall.common.auth.AuthCodeSenderManager;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthCodeManager implements AuthCodeSenderManager {
    @Autowired
    private List<AuthCodeSender> authCodeSenders;

    @Override
    public AuthCodeSender getSender(AuthCodeType type) {
        return authCodeSenders
                .stream().distinct()
                .filter(authCodeSender -> authCodeSender
                        .support()
                        .equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("AuthCodeSender not found"));
    }
}
