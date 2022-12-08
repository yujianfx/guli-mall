package cloud.stackexplode.gulimall.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 发票类型
 *
 * @author 26530
 * @date 2022/10/27
 */
public enum BillType {
    /**
     * 没有比尔
     */
    NO_BILL(0, "无发票"),
    /**
     * 纸法案
     */
    PAPER_BILL(1, "纸质发票"),
    /**
     * 电子账单
     */
    ELECTRONIC_BILL(2, "电子发票"),
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
     * 比尔类型
     *
     * @param code 代码
     * @param msg  味精
     */
    BillType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public BillType setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BillType setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
