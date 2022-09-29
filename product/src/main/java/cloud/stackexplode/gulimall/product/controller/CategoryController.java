package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.CategoryEntity;
import cloud.stackexplode.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list() {
        List<CategoryEntity> listTree = categoryService.listTree();
        return R.ok().put("data", listTree);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateDetail(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        return categoryService.removeMenuByIds(Arrays.asList(catIds)) ? R.ok() : R.error("删除失败");
    }
}
