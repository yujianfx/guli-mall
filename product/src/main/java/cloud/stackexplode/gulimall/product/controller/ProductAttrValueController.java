package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.service.ProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * spu属性值
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/productattrvalue")
public class ProductAttrValueController {
  @Autowired private ProductAttrValueService productAttrValueService;

  /** 列表 */
  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = productAttrValueService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    ProductAttrValueEntity productAttrValue = productAttrValueService.getById(id);

    return R.ok().put("productAttrValue", productAttrValue);
  }

  /** 保存 */
  @PostMapping("/save")
  public R save(@RequestBody ProductAttrValueEntity productAttrValue) {
    productAttrValueService.save(productAttrValue);

    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody ProductAttrValueEntity productAttrValue) {
    productAttrValueService.updateById(productAttrValue);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    productAttrValueService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }
}
