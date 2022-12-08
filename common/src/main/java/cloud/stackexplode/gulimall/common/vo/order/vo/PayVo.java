package cloud.stackexplode.gulimall.common.vo.order.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = false, chain = true)
public class PayVo {
    private Long outTradeNo; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String totalAmount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
