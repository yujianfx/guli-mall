package cloud.stackexplode.gulimall.common.to.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderWareResetTo {
    private Long id;
    /**
     * member_id
     */
    private Long memberId;
    /**
     * 订单号
     */
    private Long orderSn;
    private Long couponId;
    private List<OrderWareResetItem> items;

}
