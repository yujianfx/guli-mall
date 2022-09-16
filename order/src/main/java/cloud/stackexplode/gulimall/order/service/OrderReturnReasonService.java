package cloud.stackexplode.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
