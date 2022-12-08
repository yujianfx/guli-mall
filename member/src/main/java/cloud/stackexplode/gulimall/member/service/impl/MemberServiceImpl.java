package cloud.stackexplode.gulimall.member.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.common.constant.member.constant.MemberConstant;
import cloud.stackexplode.gulimall.member.dao.MemberDao;
import cloud.stackexplode.gulimall.common.entities.member.entity.MemberEntity;
import cloud.stackexplode.gulimall.common.exception.member.exception.RegisterParamAlreadyExistException;
import cloud.stackexplode.gulimall.member.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity>
        implements MemberService {
    @Autowired
    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @Override
    public Boolean register(MemberEntity memberEntity) {
        if (StringUtils.isNotEmpty(memberEntity.getMobile())) {
            if (lambdaQuery().eq(MemberEntity::getMobile, memberEntity.getMobile()).count() != 0) {
                throw new RegisterParamAlreadyExistException(MemberConstant.MOBILE);
            }
        } else if (StringUtils.isNotEmpty(memberEntity.getUserName())) {
            if (lambdaQuery().eq(MemberEntity::getUserName, memberEntity.getUserName()).count() != 0) {
                throw new RegisterParamAlreadyExistException(MemberConstant.USERNAME);
            }
        } else if (StringUtils.isNotEmpty(memberEntity.getEmail())) {
            if (lambdaQuery().eq(MemberEntity::getEmail, memberEntity.getEmail()).count() != 0) {
                throw new RegisterParamAlreadyExistException(MemberConstant.EMAIL);
            }
        }
        memberEntity
                .setCreateTime(new Date())
                .setGrowth(0)
                .setLevelId(0L)
                .setPassWord(pbkdf2PasswordEncoder.encode(memberEntity.getPassWord()))
                .setIntegration(0);
        log.info("memberEntity: {}", memberEntity);
        return save(memberEntity);
    }

    public MemberEntity login(MemberEntity memberEntity) {
        MemberEntity member = lambdaQuery()
                .eq(MemberEntity::getUserName, memberEntity.getUserName())
                .list()
                .stream()
                .filter(m -> pbkdf2PasswordEncoder
                        .matches(memberEntity.getPassWord(), m.getPassWord())).findFirst().get();
        return member;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page =
                this.page(new Query<MemberEntity>().getPage(params), new QueryWrapper<MemberEntity>());
        return new PageUtils(page);
    }
}
