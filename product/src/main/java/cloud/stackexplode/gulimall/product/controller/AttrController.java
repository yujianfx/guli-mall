package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.product.entity.AttrEntity;
import cloud.stackexplode.gulimall.product.service.AttrService;
import cloud.stackexplode.gulimall.product.vo.AttrRespVo;
import cloud.stackexplode.gulimall.product.vo.AttrVo;
import org.hibernate.validator.constraints.Range;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
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
  @RequestMapping("/list/{attrType}/{cid}")
  public R list(
      @RequestParam Map<String, Object> params,
      @PathVariable("attrType") @Range(min = 0, max = 1) Integer attrType,
      @PathVariable("cid") @NotNull Long cid) {
    PageUtils page = attrService.queryPageByCid(params, attrType, cid);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @RequestMapping("/info/{attrType}/{attrId}")
  public R info(@PathVariable("attrId") Long attrId, @PathVariable("attrType") Integer attrType) {
    AttrRespVo attr = attrService.getAttrDetailById(attrType, attrId);
    return R.ok().put("attr", attr);
  }

  /** 保存 */
  @RequestMapping("/save")
  public R save(@RequestBody AttrVo attrVo) {
    attrService.saveAttr(attrVo);

    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  public R update(@RequestBody AttrEntity attr) {
    attrService.updateById(attr);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] attrIds) {
    attrService.removeByIds(Arrays.asList(attrIds));

    return R.ok();
  }
}
