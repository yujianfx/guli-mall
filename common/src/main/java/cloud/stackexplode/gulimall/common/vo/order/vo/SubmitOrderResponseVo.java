package cloud.stackexplode.gulimall.common.vo.order.vo;


import cloud.stackexplode.gulimall.common.entities.order.entity.OrderEntity;
import lombok.Data;

@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;
}
