package cloud.stackexplode.gulimall.ware.service;

import cloud.stackexplode.gulimall.common.entities.ware.entity.WareSkuEntity;
import cloud.stackexplode.gulimall.common.to.product.SkuHasStockVo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.vo.order.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface WareSkuService extends IService<WareSkuEntity> {


    PageUtils queryPage(Map<String, Object> params);

    WareSkuEntity getBySkuId(Long id);

    List<WareSkuEntity> getListBySkuId(List<Long> id);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStocks(List<Long> ids);

    Boolean orderLockStock(WareSkuLockVo lockVo);

    void unlock(Long taskId);

    void commit(Long orderSn);

    void unlock(Long orderSn);
}
