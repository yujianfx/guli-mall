package cloud.stackexplode.gulimall.common.constant.order.rabbit;

public class WareRabbitRoutingKeyConstant {
    public static final String ORDER_CREATE_ROUTING_KEY = "order.event.create.*";
    public static final String ORDER_CLOSE_ROUTING_KEY = "order.event.close.*";
    public static final String ORDER_DL_ROUTING_KEY = "order.dl.*";
}

