package cloud.stackexplode.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
@Data @Accessors(fluent = false, chain = true)
@TableName("mq_message")
public class MqMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private String messageId;
    /**
     * JSON
     */
    private String content;
    /**
     *
     */
    private String toExchange;
    /**
     *
     */
    private String classType;
    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    private Integer messageStatus;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
}
