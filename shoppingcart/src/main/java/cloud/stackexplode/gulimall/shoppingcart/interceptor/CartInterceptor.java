package cloud.stackexplode.gulimall.shoppingcart.interceptor;

import cloud.stackexplode.gulimall.common.constant.AuthServerConstant;
import cloud.stackexplode.gulimall.common.to.session.MemberSessionTo;
import cloud.stackexplode.gulimall.common.to.shoppingcart.to.UserInfoTo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoTo> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (ObjectUtils.isEmpty(session.getAttribute(AuthServerConstant.LOGIN_USER))) {
            session.setAttribute("targetUrl", "http://windows.stackexplode.cloud:28888/api" + request.getRequestURI());
            response.sendRedirect("http://windows.stackexplode.cloud:28888/api/auth/loginView");
            return false;
        }
        MemberSessionTo memberResponseVo = (MemberSessionTo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        UserInfoTo userInfoTo = new UserInfoTo();
        if (memberResponseVo != null) {
            userInfoTo.setUserId(memberResponseVo.getId());
        }
        threadLocal.set(userInfoTo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        threadLocal.remove();
    }
}
