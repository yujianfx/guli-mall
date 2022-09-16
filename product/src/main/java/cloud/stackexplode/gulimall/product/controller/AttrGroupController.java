package cloud.stackexplode.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.stackexplode.gulimall.product.entity.AttrGroupEntity;
import cloud.stackexplode.gulimall.product.service.AttrGroupService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;

/**
 * 属性分组
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
  @Autowired private AttrGroupService attrGroupService;

  /** 列表 */
  @RequestMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = attrGroupService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @RequestMapping("/info/{attrGroupId}")
  public R info(@PathVariable("attrGroupId") Long attrGroupId) {
    AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

    return R.ok().put("attrGroup", attrGroup);
  }

  /** 保存 */
  @RequestMapping("/save")
  public R save(@RequestBody AttrGroupEntity attrGroup) {
    attrGroupService.save(attrGroup);

    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  public R update(@RequestBody AttrGroupEntity attrGroup) {
    attrGroupService.updateById(attrGroup);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] attrGroupIds) {
    attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

    return R.ok();
  }
}
