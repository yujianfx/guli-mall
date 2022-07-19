package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.HomeSubjectDao;
import top.wangudiercai.gulimall.coupon.entity.HomeSubjectEntity;
import top.wangudiercai.gulimall.coupon.service.HomeSubjectService;

@Service("homeSubjectService")
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectDao, HomeSubjectEntity>
    implements HomeSubjectService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<HomeSubjectEntity> page =
        this.page(
            new Query<HomeSubjectEntity>().getPage(params), new QueryWrapper<HomeSubjectEntity>());

    return new PageUtils(page);
  }
}
