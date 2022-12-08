package cloud.stackexplode.gulimall.common.vo.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class WareSkuLockVo {
    private Long orderSn;
    private List<OrderItemVo> locks;
}
