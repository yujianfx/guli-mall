package cloud.stackexplode.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.member.entity.MemberReceiveAddressEntity;

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
}
