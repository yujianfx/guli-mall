package cloud.stackexplode.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
