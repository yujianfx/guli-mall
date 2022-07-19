package top.wangudiercai.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 秒杀活动场次
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 21:38:58
 */
@Data
@TableName("sms_seckill_session")
public class SeckillSessionEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  /** id */
  @TableId private Long id;
  /** 场次名称 */
  private String name;
  /** 每日开始时间 */
  private Date startTime;
  /** 每日结束时间 */
  private Date endTime;
  /** 启用状态 */
  private Integer status;
  /** 创建时间 */
  private Date createTime;
}
