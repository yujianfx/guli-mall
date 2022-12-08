package cloud.stackexplode.gulimall.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 订单来源
 *
 * @author 26530
 * @date 2022/10/27
 */
public enum OrderSource {
    /**
     * 应用程序
     */
    APP(0, "app"),
    /**
     * 个人电脑
     */
    PC(1, "pc"),
    /**
     * wap
     */
    WAP(2, "wap"),
    /**
     *
     */
    MINI(3, "mini"),
    ;
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
     * 订单来源
     *
     * @param code 代码
     * @param msg  味精
     */
    OrderSource(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 代码
     *
     * @return {@link Integer}
     */
    public Integer code() {
        return code;
    }

    /**
     * 味精
     *
     * @return {@link String}
     */
    public String msg() {
        return msg;
    }
}
