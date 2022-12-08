package cloud.stackexplode.gulimall.common.to.order;

import lombok.Data;

@Data
public class OrderWareResetItem {
    private Long skuId;
    private Integer count;
    private Long wareId;
}

