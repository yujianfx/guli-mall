package top.wangudiercai.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.wangudiercai.gulimall.product.entity.CategoryEntity;
import top.wangudiercai.gulimall.product.service.CategoryService;
import top.wangudiercai.gulimall.common.utils.R;

/**
 * 商品三级分类
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
  @Autowired private CategoryService categoryService;

  /** 列表 */
  @RequestMapping("/list")
  public R list() {
    List<CategoryEntity> listTree = categoryService.listTree();
    return R.ok().put("data", listTree);
  }

  /** 信息 */
  @RequestMapping("/info/{catId}")
  public R info(@PathVariable("catId") Long catId) {
    CategoryEntity category = categoryService.getById(catId);

    return R.ok().put("category", category);
  }

  /** 保存 */
  @RequestMapping("/save")
  public R save(@RequestBody CategoryEntity category) {
    categoryService.save(category);

    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  public R update(@RequestBody CategoryEntity category) {
    categoryService.updateById(category);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] catIds) {
    categoryService.removeByIds(Arrays.asList(catIds));

    return R.ok();
  }
}
