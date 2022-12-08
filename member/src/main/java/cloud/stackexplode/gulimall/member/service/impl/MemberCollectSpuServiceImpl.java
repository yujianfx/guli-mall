package cloud.stackexplode.gulimall.member.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.member.dao.MemberCollectSpuDao;
import cloud.stackexplode.gulimall.common.entities.member.entity.MemberCollectSpuEntity;
import cloud.stackexplode.gulimall.member.service.MemberCollectSpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
