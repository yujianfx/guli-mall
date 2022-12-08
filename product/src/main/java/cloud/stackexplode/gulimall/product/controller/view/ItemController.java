package cloud.stackexplode.gulimall.product.controller.view;

import cloud.stackexplode.gulimall.product.service.SkuInfoService;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) {
        SkuItemVo skuItemVo=skuInfoService.item(skuId);
        model.addAttribute("item", skuItemVo);
        return "item";
    }
}
