package cloud.stackexplode.gulimall.common.entities.ware.entity;

import cloud.stackexplode.gulimall.common.enums.order.PaymentMethodEnum;
import cloud.stackexplode.gulimall.common.enums.ware.WareTaskStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存工作单
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
@Data
@Accessors(fluent = false, chain = true)
@TableName("wms_ware_order_task")
public class WareOrderTaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * order_id
     */
    private Long orderId;
    /**
     * order_sn
     */
    private Long orderSn;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货人电话
     */
    private String consigneeTel;
    /**
     * 配送地址
     */
    private String deliveryAddress;
    /**
     * 订单备注
     */
    private String orderComment;
    /**
     * 付款方式【 1:在线付款 2:货到付款】
     */
    private PaymentMethodEnum paymentWay;
    /**
     * 任务状态
     */
    private WareTaskStatus taskStatus;
    /**
     * 订单描述
     */
    private String orderBody;
    /**
     * 物流单号
     */
    private String trackingNo;
    /**
     * create_time
     */
    private Date createTime;
    /**
     * 仓库id
     */
    private Long wareId;
    /**
     * 工作单备注
     */
    private String taskComment;
}
