package cloud.stackexplode.gulimall.common.auth;

/**
 * 身份验证代码发送方
 *
 * @author 26530
 * @date 2022/10/17
 */
public interface AuthCodeSender {
    /**
     * 支持
     *
     * @return {@link AuthCodeType}
     */
    AuthCodeType support();

    /**
     * @param target 目标
     */
    void send(String target,String code);
}
