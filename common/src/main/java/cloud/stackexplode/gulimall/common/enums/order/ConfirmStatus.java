package cloud.stackexplode.gulimall.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ConfirmStatus {
    /**
     * 未确认
     */
    UNCONFIRMED(0, "未确认"),
    /**
     * 已确认
     */
    CONFIRMED(1, "已确认"),
    /**
     * 已取消
     */
    CANCELED(2, "已取消"),
    /**
     * 无效
     */
    INVALID(3, "无效"),
    /**
     * 退货
     */
    RETURNED(4, "退货");

    @JsonValue
    @EnumValue
    private int code;
    private String msg;

    ConfirmStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
