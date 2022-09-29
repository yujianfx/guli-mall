package cloud.stackexplode.gulimall.member.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.member.dao.GrowthChangeHistoryDao;
import cloud.stackexplode.gulimall.member.entity.GrowthChangeHistoryEntity;
import cloud.stackexplode.gulimall.member.service.GrowthChangeHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl
        extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity>
        implements GrowthChangeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistoryEntity> page =
                this.page(
                        new Query<GrowthChangeHistoryEntity>().getPage(params),
                        new QueryWrapper<GrowthChangeHistoryEntity>());

        return new PageUtils(page);
    }
}
