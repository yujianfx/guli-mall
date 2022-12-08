package cloud.stackexplode.gulimall.ware.controller;

import cloud.stackexplode.gulimall.common.entities.ware.entity.WareSkuEntity;
import cloud.stackexplode.gulimall.common.to.product.SkuHasStockVo;
import cloud.stackexplode.gulimall.common.to.ware.WareSkuTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.WareSkuLockVo;
import cloud.stackexplode.gulimall.ware.service.WareSkuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品库存
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);
        return R.ok(page);
    }

    @PostMapping("/getSkuHasStocks")
    R<List<SkuHasStockVo>> getSkuHasStocks(@RequestBody List<Long> ids) {
        List<SkuHasStockVo> skuHasStocks = wareSkuService.getSkuHasStocks(ids);
        return R.ok(skuHasStocks);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok(wareSku);
    }

    @GetMapping("/infoSukId/{sId}")
    public R infoBySukId(@PathVariable("sId") Long id) {
        WareSkuEntity wareSku = wareSkuService.getBySkuId(id);
        return R.ok(wareSku);
    }

    @PostMapping("/infoListSukId/")
    public R infoListBySukId(@RequestBody ArrayList<Long> ids) {
        return R.ok(wareSkuService.getListBySkuId(ids));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.save(wareSku);
        return R.ok();
    }

    @PostMapping("/saveSkuWare")
    public R saveSkuWare(@RequestBody List<WareSkuTo> wareSkuTos) {
        boolean res =
                wareSkuService.saveBatch(
                        wareSkuTos.stream()
                                .map(
                                        wareSkuTo -> {
                                            WareSkuEntity wareSkuEntity = new WareSkuEntity();
                                            BeanUtils.copyProperties(wareSkuTo, wareSkuEntity);
                                            return wareSkuEntity;
                                        })
                                .collect(Collectors.toList()));
        return res ? R.ok("添加默认库存成功") : R.error("添加默认库存失败");
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @PostMapping("/lockWare")
    R orderLockStock(@RequestBody WareSkuLockVo lockVo) {
        return wareSkuService.orderLockStock(lockVo) ? R.ok() : R.error("锁定库存失败");
    }
}
