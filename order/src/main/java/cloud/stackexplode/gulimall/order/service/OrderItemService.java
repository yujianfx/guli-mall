package cloud.stackexplode.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
public interface OrderItemService extends IService<OrderItemEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
