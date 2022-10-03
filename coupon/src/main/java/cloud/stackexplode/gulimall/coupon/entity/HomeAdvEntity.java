package cloud.stackexplode.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页轮播广告
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
@Data @Accessors(fluent = false, chain = true)
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 图片地址
     */
    private String pic;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 广告详情连接地址
     */
    private String url;
    /**
     * 备注
     */
    private String note;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 发布者
     */
    private Long publisherId;
    /**
     * 审核者
     */
    private Long authId;
}
