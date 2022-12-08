package cloud.stackexplode.gulimall.order.service;

import cloud.stackexplode.gulimall.common.to.mq.SeckillOrderTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.entities.order.entity.OrderEntity;
import cloud.stackexplode.gulimall.common.vo.order.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 订单
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
public interface OrderService extends IService<OrderEntity> {
    OrderEntity getOrderDetailsByOrderSn(Long orderSn);

    OrderConfirmVo confirmOrder();

    SubmitOrderResponseVo submitOrder(OrderSubmitVo submitVo);

    OrderEntity getOrderByOrderSn(Long orderSn);


    void closeOrder(Long orderSn);

    PageUtils getMemberOrderPage(Map<String, Object> params);

    PayVo getOrderPay(Long orderSn);

//    void handlerPayResult(PayAsyncVo payAsyncVo);

    void createSeckillOrder(SeckillOrderTo orderTo);

    PageUtils queryPage(Map<String, Object> params);
}
