package cloud.stackexplode.gulimall.order.controller;

import cloud.stackexplode.gulimall.common.entities.order.entity.OrderEntity;
import cloud.stackexplode.gulimall.common.exception.ware.NoStockException;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.OrderSubmitVo;
import cloud.stackexplode.gulimall.common.vo.order.vo.PayVo;
import cloud.stackexplode.gulimall.common.vo.order.vo.SubmitOrderResponseVo;
import cloud.stackexplode.gulimall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Map;

/**
 * 订单
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
@Controller
@Slf4j
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @GetMapping("/detailInfo/{orderSn}")
    public R<OrderEntity> getOrderBySn(@PathVariable("orderSn") Long orderSn) {
        OrderEntity orderEntity = orderService.getOrderDetailsByOrderSn(orderSn);
        return R.ok(orderEntity);
    }

    @RequestMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo submitVo, RedirectAttributes attributes) {
        submitVo.setWareId(1L);
        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(submitVo);
            Integer code = responseVo.getCode();
            if (code == 0) {
                return "redirect:http://windows.stackexplode.cloud:28888/api/payment/view/payView/".concat(responseVo.getOrder().getOrderSn().toString());
            } else {
                String msg = "下单失败;";
                switch (code) {
                    case 1:
                        msg += "防重令牌校验失败";
                        break;
                    case 2:
                        msg += "商品价格发生变化";
                        break;
                    default:
                        msg += "未知错误";
                }
                log.error(msg);
                attributes.addFlashAttribute("msg", msg);
                return "redirect:http://windows.stackexplode.cloud:28888/api/order/confirm/confirmView";
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NoStockException) {
                String msg = "下单失败，商品无库存";
                log.error(msg);
                attributes.addFlashAttribute("msg", msg);
            }
            return "redirect:http://windows.stackexplode.cloud:28888/api/order/confirm/confirmView";
        }
    }

    @ResponseBody
    @GetMapping("/getInfoByOrderSn/{orderSn}")
    public R<OrderEntity> infoByOrderSn(@PathVariable("orderSn") Long orderSn) {
        OrderEntity order = orderService.getOrderByOrderSn(orderSn);
        return R.ok(order);
    }

    @ResponseBody
    @GetMapping("/getOrderPay/{orderSn}")
    public R<PayVo> getOrderPay(@PathVariable("orderSn") Long orderSn) {
        return R.ok(orderService.getOrderPay(orderSn));
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);

        return R.ok(page);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);

        return R.ok(order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
