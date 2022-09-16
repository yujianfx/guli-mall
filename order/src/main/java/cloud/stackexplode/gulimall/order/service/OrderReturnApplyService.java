package cloud.stackexplode.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.order.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
