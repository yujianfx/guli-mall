package cloud.stackexplode.gulimall.auth.feign;

import cloud.stackexplode.gulimall.auth.vo.SocialUser;
import cloud.stackexplode.gulimall.auth.vo.UserLoginVo;
import cloud.stackexplode.gulimall.auth.vo.UserRegisterVo;
import cloud.stackexplode.gulimall.common.to.session.MemberSessionTo;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "member-service")
public interface MemberFeignService {

    @PostMapping("/member/register")
    @ResponseBody
    R<UserRegisterVo> register(@RequestBody UserRegisterVo registerVo);


    @PostMapping("/member/login")
    @ResponseBody
    R<MemberSessionTo> login(@RequestBody UserLoginVo loginVo);

    @RequestMapping("member/member/oauth2/login")
    @ResponseBody
    R login(@RequestBody SocialUser socialUser);
}
