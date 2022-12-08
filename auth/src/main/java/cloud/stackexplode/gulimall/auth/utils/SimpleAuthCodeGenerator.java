package cloud.stackexplode.gulimall.auth.utils;

import cloud.stackexplode.gulimall.common.auth.AuthCode;
import cloud.stackexplode.gulimall.common.auth.AuthCodeGenerator;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import org.apache.commons.lang.math.JVMRandom;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class SimpleAuthCodeGenerator implements AuthCodeGenerator {
    /**
     * 生成
     *
     * @param type 类型
     * @return {@link AuthCode}
     */
    @Override
    public AuthCode generate(AuthCodeType type) {
        Random random = new JVMRandom();
        String code = String.valueOf(random.nextInt(999999));
        return new AuthCode()
                .setCode(code)
                .setType(type)
                .setExpireTime(LocalDateTime.now().plusMinutes(15));
    }
}

