package cloud.stackexplode.gulimall.common.auth;

public interface AuthCodeGenerator {
    AuthCode generate(AuthCodeType type);
}
