package cloud.stackexplode.gulimall.common.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/** The type Product constant. */
public class ProductConstant {

  /** The enum Attr enum. */
  public enum AttrEnum {
    /** Attr type base attr enum. */
    ATTR_TYPE_BASE(1, "基本属性"),
    /** Attr type sale attr enum. */
    ATTR_TYPE_SALE(0, "销售属性");
    @JsonValue @EnumValue private int code;
    private String msg;

    AttrEnum(int code, String msg) {
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
