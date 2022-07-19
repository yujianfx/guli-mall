package top.wangudiercai.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.member.entity.MemberReceiveAddressEntity;

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
