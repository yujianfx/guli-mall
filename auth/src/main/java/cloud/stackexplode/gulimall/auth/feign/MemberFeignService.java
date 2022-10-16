package cloud.stackexplode.gulimall.auth.feign;

import cloud.stackexplode.gulimall.auth.feign.fallback.MemberFallbackService;
import cloud.stackexplode.gulimall.auth.vo.SocialUser;
import cloud.stackexplode.gulimall.auth.vo.UserLoginVo;
import cloud.stackexplode.gulimall.auth.vo.UserRegisterVo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "member-service",fallback = MemberFallbackService.class)
public interface MemberFeignService {

    @RequestMapping("member/member/register")
    R register(@RequestBody UserRegisterVo registerVo);


    @RequestMapping("member/member/login")
     R login(@RequestBody UserLoginVo loginVo);

    @RequestMapping("member/member/oauth2/login")
    R login(@RequestBody SocialUser socialUser);
}
