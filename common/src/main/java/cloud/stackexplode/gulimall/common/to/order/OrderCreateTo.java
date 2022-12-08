package cloud.stackexplode.gulimall.common.to.order;


import cloud.stackexplode.gulimall.common.entities.order.entity.OrderEntity;
import cloud.stackexplode.gulimall.common.entities.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;

}
