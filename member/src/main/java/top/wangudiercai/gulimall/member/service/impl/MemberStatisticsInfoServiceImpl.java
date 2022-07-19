package top.wangudiercai.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.member.dao.MemberStatisticsInfoDao;
import top.wangudiercai.gulimall.member.entity.MemberStatisticsInfoEntity;
import top.wangudiercai.gulimall.member.service.MemberStatisticsInfoService;

@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl
    extends ServiceImpl<MemberStatisticsInfoDao, MemberStatisticsInfoEntity>
    implements MemberStatisticsInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberStatisticsInfoEntity> page =
        this.page(
            new Query<MemberStatisticsInfoEntity>().getPage(params),
            new QueryWrapper<MemberStatisticsInfoEntity>());

    return new PageUtils(page);
  }
}
