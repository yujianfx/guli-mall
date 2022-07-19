package top.wangudiercai.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.member.dao.MemberDao;
import top.wangudiercai.gulimall.member.entity.MemberEntity;
import top.wangudiercai.gulimall.member.service.MemberService;

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
