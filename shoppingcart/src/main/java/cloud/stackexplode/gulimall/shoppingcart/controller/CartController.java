package cloud.stackexplode.gulimall.shoppingcart.controller;

import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartItemVo;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartVo;
import cloud.stackexplode.gulimall.shoppingcart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("shoppingcart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/shopingCartView")
    public String getCartList(Model model) {
        CartVo cartVo = cartService.getCart();
        model.addAttribute("cart", cartVo);
        return "cartList";
    }

    @RequestMapping("/successView")
    public String success() {
        return "success";
    }

    /**
     * 添加商品到购物车
     * RedirectAttributes.addFlashAttribute():将数据放在session中，可以在页面中取出，但是只能取一次
     * RedirectAttributes.addAttribute():将数据放在url后面
     *
     * @return
     */
    @RequestMapping("/addCartItem")
    public String addCartItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, RedirectAttributes attributes) {
        cartService.addCartItem(skuId, num);
        attributes.addAttribute("skuId", skuId);
        return "redirect:http://windows.stackexplode.cloud:28888/api/shoppingcart/addCartItemSuccess";
    }

    @RequestMapping("/addCartItemSuccess")
    public String addCartItemSuccess(@RequestParam("skuId") Long skuId, Model model) {
        CartItemVo cartItemVo = cartService.getCartItem(skuId);
        model.addAttribute("cartItem", cartItemVo);
        return "success";
    }


    @RequestMapping("/checkCart")
    public String checkCart(@RequestParam("skuId") Long skuId) {
        cartService.checkCart(skuId);
        return "redirect:http://windows.stackexplode.cloud:28888/api/shoppingcart/shopingCartView";
    }

    @RequestMapping("/countItem")
    public String changeItemCount(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return "redirect:http://windows.stackexplode.cloud:28888/api/shoppingcart/shopingCartView";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://windows.stackexplode.cloud:28888/api/shoppingcart/shopingCartView";
    }

    @ResponseBody
    @RequestMapping("/getCheckedItems")
    public R<List<CartItemVo>> getCheckedItems() {
        return R.ok(cartService.getCheckedItems().get());
    }
}
