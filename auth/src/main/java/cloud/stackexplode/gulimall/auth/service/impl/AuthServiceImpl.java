package cloud.stackexplode.gulimall.auth.service.impl;

import cloud.stackexplode.gulimall.auth.config.AuthCodeManager;
import cloud.stackexplode.gulimall.auth.exception.AuthCodeException;
import cloud.stackexplode.gulimall.auth.service.AuthService;
import cloud.stackexplode.gulimall.common.auth.AuthCodeGenerator;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import cloud.stackexplode.gulimall.common.constant.AuthServerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthCodeManager authCodeManager;
    @Autowired
    private AuthCodeGenerator authCodeGenerator;

    @Async
    @Override
    public void sendCode(String target, AuthCodeType type) {
        String code = authCodeGenerator.generate(type).getCode();

        RequestContextHolder
                .currentRequestAttributes()
                .setAttribute("code", code, RequestAttributes.SCOPE_SESSION);
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        if (Boolean.FALSE.equals(valueOperations.setIfAbsent(AuthServerConstant.AUTH_CODE_LIMIT_PREFIX + target, "1", 1, TimeUnit.MINUTES))) {
            throw new AuthCodeException("AuthCode limit 验证码请求太频繁");
        }
        log.info("code:{}", code);
        authCodeManager.getSender(type).send(target, code);
    }
}
