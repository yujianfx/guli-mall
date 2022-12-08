package cloud.stackexplode.gulimall.member.service.impl;

import cloud.stackexplode.gulimall.common.entities.member.entity.MemberReceiveAddressEntity;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.member.dao.MemberReceiveAddressDao;
import cloud.stackexplode.gulimall.member.service.MemberReceiveAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {
    @Override
    public List<MemberReceiveAddressEntity> getAllByUserId(Long userId) {
        return this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", userId));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddressEntity> page = this.page(new Query<MemberReceiveAddressEntity>().getPage(params), new QueryWrapper<MemberReceiveAddressEntity>());

        return new PageUtils(page);
    }
}
