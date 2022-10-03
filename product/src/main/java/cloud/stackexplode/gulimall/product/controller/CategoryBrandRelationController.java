package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.CategoryBrandRelationEntity;
import cloud.stackexplode.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
  @Autowired private CategoryBrandRelationService categoryBrandRelationService;

  @GetMapping("/list/{brandId}")
  public R cbRelationList(
      @RequestParam Map<String, Object> params, @PathVariable("brandId") Long bId) {
    PageUtils page = categoryBrandRelationService.queryPageByBrandId(params, bId);
    return R.ok().put("page", page);
  }

  @GetMapping("/brandsList/{cId}")
  public R cbRelationListBy(
      @RequestParam Map<String, Object> params, @PathVariable("cId") Long cId) {
    PageUtils page = categoryBrandRelationService.queryPageByCid(params, cId);
    return R.ok().put("page", page);
  }
  /** 信息 */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

    return R.ok().put("categoryBrandRelation", categoryBrandRelation);
  }

  @PostMapping("/save")
  public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.saveDetil(categoryBrandRelation);
    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.updateById(categoryBrandRelation);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    categoryBrandRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }
}
