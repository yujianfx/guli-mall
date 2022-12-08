package cloud.stackexplode.gulimall.order.interceptor;


import cloud.stackexplode.gulimall.common.constant.AuthServerConstant;
import cloud.stackexplode.gulimall.common.to.session.MemberSessionTo;
import cloud.stackexplode.gulimall.common.vo.MemberResponseVo;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器，未登录的用户不能进入订单服务
 */
public class LoginInterceptor implements HandlerInterceptor {
    public static ThreadLocal<MemberSessionTo> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        AntPathMatcher matcher = new AntPathMatcher();
//        boolean match1 = matcher.match("/order/order/infoByOrderSn/**", requestURI);
//        boolean match2 = matcher.match("/payed/**", requestURI);
//        if (match1 || match2) {
//            return true;
//        }

        HttpSession session = request.getSession();
        MemberSessionTo memberSessionTo = (MemberSessionTo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (memberSessionTo != null) {
            loginUser.set(memberSessionTo);
            return true;
        }
        return  true;
//        else {
//            session.setAttribute("msg", "请先登录");
//            response.sendRedirect("http://windows.stackexplode.cloud:28888/api/auth/loginView");
//            return false;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        loginUser.remove();
    }
}
