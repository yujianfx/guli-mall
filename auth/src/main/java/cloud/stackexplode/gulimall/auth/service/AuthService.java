package cloud.stackexplode.gulimall.auth.service;

import cloud.stackexplode.gulimall.common.auth.AuthCodeType;

public interface AuthService {
    void sendCode(String target, AuthCodeType type);
}
