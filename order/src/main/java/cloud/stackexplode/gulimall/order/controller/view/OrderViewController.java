package cloud.stackexplode.gulimall.order.controller.view;

import cloud.stackexplode.gulimall.common.vo.order.vo.OrderConfirmVo;
import cloud.stackexplode.gulimall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order/confirm")
@Slf4j
public class OrderViewController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/confirmView")
    public String confirmOrder(Model model, HttpServletRequest httpServletRequest) {
        log.info("uri{},url{}", httpServletRequest.getRequestURI(), httpServletRequest.getRequestURL());
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("confirmOrder", confirmVo);
        return "confirm";
    }
}
