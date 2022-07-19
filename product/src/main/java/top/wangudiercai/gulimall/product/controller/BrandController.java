package top.wangudiercai.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.wangudiercai.gulimall.product.entity.BrandEntity;
import top.wangudiercai.gulimall.product.service.BrandService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.R;

/**
 * 品牌
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
  @Autowired private BrandService brandService;

  /** 列表 */
  @RequestMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = brandService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @RequestMapping("/info/{brandId}")
  public R info(@PathVariable("brandId") Long brandId) {
    BrandEntity brand = brandService.getById(brandId);

    return R.ok().put("brand", brand);
  }

  /** 保存 */
  @RequestMapping("/save")
  public R save(@RequestBody BrandEntity brand) {
    brandService.save(brand);

    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  public R update(@RequestBody BrandEntity brand) {
    brandService.updateById(brand);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] brandIds) {
    brandService.removeByIds(Arrays.asList(brandIds));

    return R.ok();
  }
}
