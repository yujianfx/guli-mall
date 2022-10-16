package cloud.stackexplode.gulimall.common.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * spu常数
 *
 * @author 26530
 * @date 2022 /10/05
 */
public class SpuConstant {
  /**
   * spu状态
   *
   * @author 26530
   * @date 2022 /10/05
   */
  public enum SpuStatus {
    /** 创建 */
    CREATE(0, "新建"),
    SALEING(1, "已上架"),
    STOP(2, "已下架");
    /** 代码 */
    @EnumValue @JsonValue private int code;
    /** 味精 */
    private String msg;

    /**
     * spu状态
     *
     * @param code 代码
     * @param msg 味精
     */
    SpuStatus(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    /**
     * 获取代码
     *
     * @return int code
     */
    public int getCode() {
      return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     * @return {@link SpuStatus}
     */
    public SpuStatus setCode(int code) {
      this.code = code;
      return this;
    }

    /**
     * Gets msg.
     *
     * @return {@link String}
     */
    public String getMsg() {
      return msg;
    }

    /**
     * 设置味精
     *
     * @param msg 味精
     * @return {@link SpuStatus}
     */
    public SpuStatus setMsg(String msg) {
      this.msg = msg;
      return this;
    }
  }
}
