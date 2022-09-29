package cloud.stackexplode.gulimall.member.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.member.dao.MemberLoginLogDao;
import cloud.stackexplode.gulimall.member.entity.MemberLoginLogEntity;
import cloud.stackexplode.gulimall.member.service.MemberLoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity>
        implements MemberLoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLoginLogEntity> page =
                this.page(
                        new Query<MemberLoginLogEntity>().getPage(params),
                        new QueryWrapper<MemberLoginLogEntity>());

        return new PageUtils(page);
    }
}
