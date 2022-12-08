package cloud.stackexplode.gulimall.common.entities.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀活动
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
@Data @Accessors(fluent = false, chain = true)
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 开始日期
     */
    private Date startTime;
    /**
     * 结束日期
     */
    private Date endTime;
    /**
     * 上下线状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long userId;
}
