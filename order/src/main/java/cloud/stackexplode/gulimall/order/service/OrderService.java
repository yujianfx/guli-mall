package cloud.stackexplode.gulimall.order.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.order.entity.OrderEntity;
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

    PageUtils queryPage(Map<String, Object> params);
}
