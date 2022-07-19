package top.wangudiercai.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.member.entity.MemberStatisticsInfoEntity;

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
