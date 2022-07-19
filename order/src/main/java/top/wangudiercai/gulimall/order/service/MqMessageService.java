package top.wangudiercai.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.order.entity.MqMessageEntity;

import java.util.Map;

/**
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
public interface MqMessageService extends IService<MqMessageEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
