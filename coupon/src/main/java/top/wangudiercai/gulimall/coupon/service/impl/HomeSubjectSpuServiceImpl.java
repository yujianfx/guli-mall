package top.wangudiercai.gulimall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.coupon.dao.HomeSubjectSpuDao;
import top.wangudiercai.gulimall.coupon.entity.HomeSubjectSpuEntity;
import top.wangudiercai.gulimall.coupon.service.HomeSubjectSpuService;

@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity>
    implements HomeSubjectSpuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<HomeSubjectSpuEntity> page =
        this.page(
            new Query<HomeSubjectSpuEntity>().getPage(params),
            new QueryWrapper<HomeSubjectSpuEntity>());

    return new PageUtils(page);
  }
}
