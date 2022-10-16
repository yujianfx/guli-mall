package cloud.stackexplode.gulimall.common.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type Ware constant.
 */
public class WareConstant {

  /**
   * The enum Purchase status enum.
   */
public enum PurchaseStatusEnum {
    /**
     *Created purchase status enum.
     */
CREATED(0, "新建"),
    /**
     *Assigned purchase status enum.
     */
ASSIGNED(1, "已分配"),
    /**
     *Receive purchase status enum.
     */
RECEIVE(2, "已领取"),
    /**
     *Finish purchase status enum.
     */
FINISH(3, "已完成"),
    /**
     *Haserror purchase status enum.
     */
HASERROR(4, "有异常");
    @JsonValue @EnumValue private final int code;
    private final String msg;

    PurchaseStatusEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
public int getCode() {
      return code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
public String getMsg() {
      return msg;
    }
  }

  /**
   * The enum Purchase detail status enum.
   */
public enum PurchaseDetailStatusEnum {
    /**
     *Created purchase detail status enum.
     */
CREATED(0, "新建"),
    /**
     *Assigned purchase detail status enum.
     */
ASSIGNED(1, "已分配"),
    /**
     *Buying purchase detail status enum.
     */
BUYING(2, "正在采购"),
    /**
     *Finish purchase detail status enum.
     */
FINISH(3, "已完成"),
    /**
     *Haserror purchase detail status enum.
     */
HASERROR(4, "采购失败");
    @JsonValue @EnumValue private int code;
    private String msg;

    PurchaseDetailStatusEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
public int getCode() {
      return code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
public String getMsg() {
      return msg;
    }
  }
}
