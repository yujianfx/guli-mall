package cloud.stackexplode.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.member.dao.MemberDao;
import cloud.stackexplode.gulimall.member.entity.MemberEntity;
import cloud.stackexplode.gulimall.member.service.MemberService;

@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity>
    implements MemberService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberEntity> page =
        this.page(new Query<MemberEntity>().getPage(params), new QueryWrapper<MemberEntity>());

    return new PageUtils(page);
  }
}
