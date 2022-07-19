package top.wangudiercai.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
