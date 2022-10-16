package cloud.stackexplode.gulimall.product.controller.view;

import cloud.stackexplode.gulimall.product.entity.CategoryEntity;
import cloud.stackexplode.gulimall.product.service.CategoryService;
import cloud.stackexplode.gulimall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("product")
@Controller
public class IndexController {
  @Autowired private CategoryService categoryService;

  @GetMapping(path = {"/", "index", "index.html"})
  public String index(Model model) {
    List<CategoryEntity> categories = categoryService.getLevel1Categories();
    model.addAttribute("categories", categories);
    return "index";
  }
  @GetMapping("index/json/catelog.json")
  @ResponseBody
  public Map<String, List<Catelog2Vo>> getCategoryMap() {
    return categoryService.getCatelogJsonDbWithSpringCache();
  }
}
