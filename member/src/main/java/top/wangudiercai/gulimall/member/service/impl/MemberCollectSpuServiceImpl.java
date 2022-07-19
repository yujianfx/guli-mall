package top.wangudiercai.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.member.dao.MemberCollectSpuDao;
import top.wangudiercai.gulimall.member.entity.MemberCollectSpuEntity;
import top.wangudiercai.gulimall.member.service.MemberCollectSpuService;

@Service("memberCollectSpuService")
public class MemberCollectSpuServiceImpl
    extends ServiceImpl<MemberCollectSpuDao, MemberCollectSpuEntity>
    implements MemberCollectSpuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberCollectSpuEntity> page =
        this.page(
            new Query<MemberCollectSpuEntity>().getPage(params),
            new QueryWrapper<MemberCollectSpuEntity>());

    return new PageUtils(page);
  }
}
