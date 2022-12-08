package cloud.stackexplode.gulimall.common.enums.ware;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 制品订单任务详细状况
 *
 * @author 26530
 * @date 2022/11/07
 */
public enum WareTaskDetailStatus {
    /**
     * 新
     */
    NEW(0, "新建"),
    /**
     * 锁着
     */
    LOCKED(1, "已锁定"),
    /**
     * 解锁
     */
    UNLOCKED(2, "已解锁"),
    /**
     * 储存
     */
    SUBMITED(3, "已入库"),
    ;
    /**
     * 代码
     */
    @JsonValue
    @EnumValue
    private Integer code;
    /**
     * 描述
     */
    private String describe;

    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     * @return {@link WareTaskDetailStatus}
     */
    public WareTaskDetailStatus setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 得到描述
     *
     * @return {@link String}
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 集描述
     *
     * @param describe 描述
     * @return {@link WareTaskDetailStatus}
     */
    public WareTaskDetailStatus setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    /**
     * 制品订单任务详细状况
     *
     * @param code     代码
     * @param describe 描述
     */
    WareTaskDetailStatus(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}
