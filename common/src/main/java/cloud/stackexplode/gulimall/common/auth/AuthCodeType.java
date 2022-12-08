package cloud.stackexplode.gulimall.common.auth;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 身份验证代码类型
 *
 * @author 26530
 * @date 2022/10/16
 */
public enum AuthCodeType {
    /** 短信 */
    SMS(0,"sms"),
    /** 电子邮件 */
    EMAIL(1,"email");
    /** 代码 */
    @JsonValue
    private Integer code;
    /** 类型 */
    private String type;

    /**
     * 身份验证代码类型
     *
     * @param i 我
     * @param sms 短信
     */
    AuthCodeType(Integer i, String sms) {
    }

    /**
     * 获取代码
     *
     * @return {@link Integer}
     *
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     * @return {@link AuthCodeType}
     */
    public AuthCodeType setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 得到类型
     *
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * 集类型
     *
     * @param type 类型
     * @return {@link AuthCodeType}
     */
    public AuthCodeType setType(String type) {
        this.type = type;
        return this;
    }
}
