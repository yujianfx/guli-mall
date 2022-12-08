package cloud.stackexplode.gulimall.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 支付方式枚举
 *
 * @author 26530
 * @date 2022/10/27
 */
public enum PaymentMethodEnum {
    /**
     * 支付宝
     */
    ALIPAY(1, "支付宝"),
    /**
     * 微信
     */
    WECHAT(2, "微信"),
    /**
     * 银联
     */
    UNIONPAY(3, "银联"),
    /**
     * 货到付款
     */
    CASH_ON_DELIVERY(4, "货到付款");;
    /**
     * 代码
     */
    @JsonValue
    @EnumValue
    private Integer code;
    /**
     * 味精
     */
    private String msg;

    /**
     * 支付方式枚举
     *
     * @param code 代码
     * @param msg  味精
     */
    PaymentMethodEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
     * 得到味精
     *
     * @return {@link String}
     */
    public String getMsg() {
        return msg;
    }
}

