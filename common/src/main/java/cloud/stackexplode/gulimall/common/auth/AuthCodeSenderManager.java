package cloud.stackexplode.gulimall.common.auth;

/**
 * @author 26530
 */
public interface AuthCodeSenderManager {
    AuthCodeSender getSender(AuthCodeType type);
}
