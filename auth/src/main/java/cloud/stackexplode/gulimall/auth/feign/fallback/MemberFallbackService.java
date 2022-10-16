package cloud.stackexplode.gulimall.auth.feign.fallback;


import cloud.stackexplode.gulimall.auth.feign.MemberFeignService;
import cloud.stackexplode.gulimall.auth.vo.SocialUser;
import cloud.stackexplode.gulimall.auth.vo.UserLoginVo;
import cloud.stackexplode.gulimall.auth.vo.UserRegisterVo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.stereotype.Service;

@Service
public class MemberFallbackService implements MemberFeignService {
    @Override
    public R register(UserRegisterVo registerVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(UserLoginVo loginVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(SocialUser socialUser) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }
}
