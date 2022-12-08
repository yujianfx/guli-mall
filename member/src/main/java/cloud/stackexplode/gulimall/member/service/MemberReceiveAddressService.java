package cloud.stackexplode.gulimall.member.service;

import cloud.stackexplode.gulimall.common.entities.member.entity.MemberReceiveAddressEntity;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MemberReceiveAddressEntity> getAllByUserId(Long userId);
}
