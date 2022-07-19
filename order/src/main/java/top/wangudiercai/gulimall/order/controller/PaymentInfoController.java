package top.wangudiercai.gulimall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.wangudiercai.gulimall.order.entity.PaymentInfoEntity;
import top.wangudiercai.gulimall.order.service.PaymentInfoService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.R;

/**
 * 支付信息表
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {
  @Autowired private PaymentInfoService paymentInfoService;

  /** 列表 */
  @RequestMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = paymentInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);

    return R.ok().put("paymentInfo", paymentInfo);
  }

  /** 保存 */
  @RequestMapping("/save")
  public R save(@RequestBody PaymentInfoEntity paymentInfo) {
    paymentInfoService.save(paymentInfo);

    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  public R update(@RequestBody PaymentInfoEntity paymentInfo) {
    paymentInfoService.updateById(paymentInfo);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    paymentInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }
}
