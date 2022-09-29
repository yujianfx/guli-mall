package cloud.stackexplode.gulimall.member.dao;

import cloud.stackexplode.gulimall.member.entity.GrowthChangeHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
@Mapper
public interface GrowthChangeHistoryDao extends BaseMapper<GrowthChangeHistoryEntity> {
}
