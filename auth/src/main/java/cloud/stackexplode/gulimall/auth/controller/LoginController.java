package cloud.stackexplode.gulimall.auth.controller;

import cloud.stackexplode.gulimall.auth.feign.MemberFeignService;
import cloud.stackexplode.gulimall.auth.service.AuthService;
import cloud.stackexplode.gulimall.auth.vo.UserLoginVo;
import cloud.stackexplode.gulimall.auth.vo.UserRegisterVo;
import cloud.stackexplode.gulimall.common.auth.AuthCodeType;
import cloud.stackexplode.gulimall.common.constant.AuthServerConstant;
import cloud.stackexplode.gulimall.common.to.session.MemberSessionTo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthService authService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MemberFeignService memberFeignService;


    @GetMapping("/loginView")
    public String loginPage(HttpSession session) {
        if (session.getAttribute(AuthServerConstant.LOGIN_USER) == null) {
            return "login";
        } else {
            return "redirect:http://windows.stackexplode.cloud:28888/api/product/";
        }
    }

    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes attributes, HttpSession session) {
        R<MemberSessionTo> r = memberFeignService.login(vo);
        if (r.getCode().equals(StatusCode.SUCCESS)) {
            session.setAttribute(AuthServerConstant.LOGIN_USER, r.getData());
            return session.getAttribute("targetUrl") == null ? "redirect:http://windows.stackexplode.cloud:28888/api/product/" : "redirect:" + session.getAttribute("targetUrl");
        } else {

            String msg = r.getMsg();
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", msg);
            attributes.addFlashAttribute("errors", errors);
            return "redirect:http://windows.stackexplode.cloud:28888/api/auth/loginView";
        }
    }

    @GetMapping("/sendCode")
    @ResponseBody
    public R<Object> sendCode(@Valid UserRegisterVo registerVo, BindingResult result) {
        AuthCodeType authCodeType = result.hasFieldErrors("target") ? AuthCodeType.SMS : AuthCodeType.EMAIL;
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
        authService.sendCode(registerVo.getTarget(), authCodeType);
        return R.ok();
    }


    @PostMapping(value = "/register")
    public String register(UserRegisterVo registerVo, BindingResult result, RedirectAttributes attributes, HttpSession httpSession) {
        log.warn("registerVo: {}", registerVo);
        AuthCodeType authCodeType = result.hasFieldErrors("target") ? AuthCodeType.SMS : AuthCodeType.EMAIL;
        registerVo.setMobile(AuthCodeType.EMAIL.equals(authCodeType) ? null : registerVo.getMobile());
        registerVo.setEmail(AuthCodeType.SMS.equals(authCodeType) ? null : registerVo.getEmail());
        Map<String, String> errors = new HashMap<>();
        if (result.hasFieldErrors("userName") || result.hasFieldErrors("passWord") || result.hasFieldErrors("code")) {
            log.info("register error: {}", result.getFieldErrors());
            result.getFieldErrors().forEach(item -> {
                errors.put(item.getField(), item.getDefaultMessage());
                attributes.addFlashAttribute("errors", errors);
            });
            return "redirect:http://windows.stackexplode.cloud:28888/api/auth/registerView";
        } else {
            String redisKey = (AuthCodeType.EMAIL.equals(authCodeType)
                    ? AuthServerConstant.MAIL_CODE_KEY_PREFIX + registerVo.getEmail() : AuthServerConstant.SMS_CODE_KEY_PREFIX + registerVo.getMobile());
            String code = String.valueOf(httpSession.getAttribute("code"));
            if (!StringUtils.isEmpty(code) && registerVo.getCode().equals(code)) {
                redisTemplate.delete(redisKey);
                R r = memberFeignService.register(registerVo);
                if (r.getCode().equals(StatusCode.SUCCESS)) {
                    //调用成功，重定向登录页
                    return "redirect:http://windows.stackexplode.cloud:28888/api/auth/loginView";
                } else {
                    //调用失败，返回注册页并显示错误信息

                    errors.put("msg", r.getMsg());
                    attributes.addFlashAttribute("errors", errors);
                    return "redirect:http://windows.stackexplode.cloud:28888/api/auth/registerView";
                }
            } else {
                //2.2 验证码错误
                errors.put("code", "验证码错误");
                attributes.addFlashAttribute("errors", errors);
                return "redirect:http://windows.stackexplode.cloud:28888/api/auth/registerView";
            }
        }

    }
}
