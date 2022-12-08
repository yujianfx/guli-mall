package cloud.stackexplode.gulimall.common.to.order;

import cloud.stackexplode.gulimall.common.enums.order.BillType;
import cloud.stackexplode.gulimall.common.enums.order.OrderSource;
import cloud.stackexplode.gulimall.common.enums.order.OrderStatus;
import cloud.stackexplode.gulimall.common.enums.order.PaymentMethodEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderTo {
    private Long id;
    /**
     * member_id
     */
    private Long memberId;
    /**
     * 订单号
     */
    private Long orderSn;
    /**
     * 使用的优惠券
     */
    private Long couponId;
    /**
     * create_time
     */
    private Date createTime;
    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    private OrderStatus status;
}
