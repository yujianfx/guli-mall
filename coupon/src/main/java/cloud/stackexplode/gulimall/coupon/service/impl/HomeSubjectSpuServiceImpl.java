package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.HomeSubjectSpuDao;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.HomeSubjectSpuEntity;
import cloud.stackexplode.gulimall.coupon.service.HomeSubjectSpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
