package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.BrandEntity;
import cloud.stackexplode.gulimall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

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
  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = brandService.queryPage(params);
    return R.ok().put("page", page);
  }

  /** 信息 */
  @GetMapping("/info/{brandId}")
  public R info(@PathVariable("brandId") Long brandId) {
    BrandEntity brand = brandService.getById(brandId);

    return R.ok().put("brand", brand);
  }

  /** 保存 */
  @PostMapping("/save")
  public R save(@RequestBody BrandEntity brand) {
    brandService.save(brand);

    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody BrandEntity brand) {
    brandService.updateDetail(brand);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] brandIds) {
    brandService.removeByIds(Arrays.asList(brandIds));

    return R.ok();
  }
}
