package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.HomeSubjectDao;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.HomeSubjectEntity;
import cloud.stackexplode.gulimall.coupon.service.HomeSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
