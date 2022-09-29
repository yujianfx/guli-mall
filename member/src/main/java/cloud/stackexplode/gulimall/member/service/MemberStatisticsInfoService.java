package cloud.stackexplode.gulimall.member.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.member.entity.MemberStatisticsInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
