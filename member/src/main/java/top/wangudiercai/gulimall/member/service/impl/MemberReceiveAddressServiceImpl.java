package top.wangudiercai.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.member.dao.MemberReceiveAddressDao;
import top.wangudiercai.gulimall.member.entity.MemberReceiveAddressEntity;
import top.wangudiercai.gulimall.member.service.MemberReceiveAddressService;

@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl
    extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity>
    implements MemberReceiveAddressService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberReceiveAddressEntity> page =
        this.page(
            new Query<MemberReceiveAddressEntity>().getPage(params),
            new QueryWrapper<MemberReceiveAddressEntity>());

    return new PageUtils(page);
  }
}
