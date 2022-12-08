package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.entities.product.entity.AttrEntity;
import cloud.stackexplode.gulimall.common.entities.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.service.AttrService;
import cloud.stackexplode.gulimall.common.vo.product.vo.AttrRespVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.AttrVo;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
  @Autowired private AttrService attrService;

  /** 列表 */
  @GetMapping("/list/{attrType}/{cid}")
  public R list(
      @RequestParam Map<String, Object> params,
      @PathVariable("attrType") @Range(min = 0, max = 1) Integer attrType,
      @PathVariable("cid") @NotNull Long cid) {
    PageUtils page = attrService.queryPageByCid(params, attrType, cid);

    return R.ok(page);
  }

  @GetMapping("/listBySpuId/{sId}")
  public R listBySpuId(@PathVariable("sId") @NotNull Long sId) {
    List<ProductAttrValueEntity> productAttrValueEntities = attrService.queryPageBySpuId(sId);
    return R.ok(productAttrValueEntities);
  }
  /** 信息 */
  @GetMapping("/info/{attrType}/{attrId}")
  public R info(@PathVariable("attrId") Long attrId, @PathVariable("attrType") Integer attrType) {
    AttrRespVo attr = attrService.getAttrDetailById(attrType, attrId);
    return R.ok( attr);
  }

  /** 保存 */
  @PostMapping("/save")
  public R save(@RequestBody AttrVo attrVo) {
    attrService.saveAttr(attrVo);

    return R.ok();
  }

  /** 修改 */
  @PutMapping("/update")
  public R update(@RequestBody AttrEntity attr) {
    attrService.updateById(attr);

    return R.ok();
  }

  /** 删除 */
  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] attrIds) {
    attrService.removeByIds(Arrays.asList(attrIds));

    return R.ok();
  }
}
