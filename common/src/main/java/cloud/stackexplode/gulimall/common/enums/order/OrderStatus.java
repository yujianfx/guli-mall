package cloud.stackexplode.gulimall.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    /**
     * 0-待付款
     */
    CREATE_NEW(0, "待付款"),
    /**
     * 1-已付款
     */
    PAYED(1, "已付款"),
    /**
     * 2-已发货
     */
    SENDED(2, "已发货"),
    /**
     * 3-已完成
     */
    RECIEVED(3, "已完成"),
    /**
     * 4-已关闭
     */
    CANCLED(4, "已关闭"),
    /**
     * 5-无效订单
     */
    INVALID(5, "无效订单"),
    /**
     * 6-已退货
     */
    RETURNED(6, "已退货");
    @JsonValue
    @EnumValue
    private int code;
    private String msg;

    OrderStatus(int code, String msg) {
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
