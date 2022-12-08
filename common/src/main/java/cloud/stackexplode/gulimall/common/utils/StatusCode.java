package cloud.stackexplode.gulimall.common.utils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 状态码
 *
 * @author 26530
 * @date 2022/10/20
 */
public enum StatusCode {

    /**
     * 成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 错误
     */
    ERROR(500, "操作失败"),
    /**
     * 验证错误
     */
    VALIDATE_ERROR(400, "参数校验失败"),
    /**
     * 未经身份验证
     */
    UNAUTHENTICATED(401, "未登录"),
    /**
     * unauthorise
     */
    UNAUTHORISE(403, "未授权"),
    /**
     * 太频繁
     */
    TOO_FREQUENT(405, "操作过于频繁"),
    /**
     * 代码错误
     */
    CODE_ERROR(406, "验证码错误"),
    /**
     * 代码过期
     */
    CODE_EXPIRED(407, "验证码过期"),
    /**
     * 代码频繁
     */
    CODE_FREQUENT(408, "验证码发送频繁"),
    /**
     * 代码不存在
     */
    CODE_NOT_EXIST(409, "验证码不存在"),
    /**
     * 代码不匹配
     */
    CODE_NOT_MATCH(410, "验证码不匹配"),
    /**
     * 代码不发送
     */
    CODE_NOT_SEND(411, "验证码未发送"),
    /**
     * 代码发送错误
     */
    CODE_SEND_ERROR(412, "验证码发送失败"),
    /**
     * 代码发送频繁
     */
    CODE_SEND_FREQUENT(413, "验证码发送频繁"),
    /**
     * 代码发送限制
     */
    CODE_SEND_LIMIT(414, "验证码发送次数超限"),
    /**
     * 系统繁忙
     */
    SYSTEM_BUSY(503, "系统繁忙"),
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(500, "未知异常"),
    /**
     * 无效令牌
     */
    INVALID_TOKEN(2001, "访问令牌不合法"),
    /**
     * 拒绝访问
     */
    ACCESS_DENIED(2003, "没有权限访问该资源"),
    /**
     * 客户端身份验证失败
     */
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    /**
     * 格兰特不支持类型
     */
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式");

    /**
     * 代码
     */
    @JsonValue
    private final Integer code;
    /**
     * 消息
     */
    private final String message;

    /**
     * 状态码
     *
     * @param code    代码
     * @param message 消息
     */
    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }

}
