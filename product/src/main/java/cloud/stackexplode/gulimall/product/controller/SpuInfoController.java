package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.entity.SpuInfoEntity;
import cloud.stackexplode.gulimall.product.service.SpuInfoService;
import cloud.stackexplode.gulimall.product.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
  @Autowired private SpuInfoService spuInfoService;

  /** 列表 */
  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuInfoService.queryPage(params);
    return R.ok().put("page", page);
  }

  /** 信息 */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SpuInfoEntity spuInfo = spuInfoService.getById(id);

    return R.ok().put("spuInfo", spuInfo);
  }

  /** 保存 */
  @PostMapping("/save")
  public R save(@RequestBody SpuVo spuVo) throws Exception {
    return spuInfoService.saveSpuDetail(spuVo) ? R.ok() : R.error();
  }
  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody SpuInfoEntity spuInfo) {
    spuInfoService.updateById(spuInfo);
    return R.ok();
  }

  @PutMapping("/updateBaseAttrsBySpuId{spuId}")
  public R updateBaseAttrsBySpuId(
      @PathVariable Long spuId, @RequestBody List<ProductAttrValueEntity> productAttrValueEntity) {
    productAttrValueEntity.forEach(
        pses -> pses.setSpuId(spuId));
    spuInfoService.updateBaseAttrsBySpuId(productAttrValueEntity);
    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    spuInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }
}
