package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SkuInfoDao;
import cloud.stackexplode.gulimall.common.entities.product.entity.SkuImagesEntity;
import cloud.stackexplode.gulimall.common.entities.product.entity.SkuInfoEntity;
import cloud.stackexplode.gulimall.common.entities.product.entity.SpuInfoDescEntity;
//import cloud.stackexplode.gulimall.product.feign.SeckillFeignService;
import cloud.stackexplode.gulimall.product.service.*;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuItemSaleAttrVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuItemVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity>
    implements SkuInfoService {
  @Autowired private ThreadPoolTaskExecutor executor;
  @Autowired private SkuImagesService skuImagesService;
  @Autowired private SkuSaleAttrValueService skuSaleAttrValueService;
  @Autowired private SpuInfoDescService spuInfoDescService;
  @Autowired private ProductAttrValueService productAttrValueService;
//  @Autowired private SeckillFeignService seckillFeignService;

  @Override
  public SkuItemVo item(Long skuId) {
    SkuItemVo skuItemVo = new SkuItemVo();
    CompletableFuture<SkuInfoEntity> infoFuture =
        CompletableFuture.supplyAsync(
            () -> {
              // 1、sku基本信息的获取  pms_sku_info
              SkuInfoEntity skuInfoEntity = this.getById(skuId);
              skuItemVo.setInfo(skuInfoEntity);
              return skuInfoEntity;
            },
            executor);

    // 2、sku的图片信息    pms_sku_images
    CompletableFuture<Void> imageFuture =
        CompletableFuture.runAsync(
            () -> {
              List<SkuImagesEntity> skuImagesEntities =
                  skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
              skuItemVo.setImages(skuImagesEntities);
            },
            executor);

    // 3、获取spu的销售属性组合-> 依赖1 获取spuId
    CompletableFuture<Void> saleFuture =
        infoFuture.thenAcceptAsync(
            (info) -> {
              List<SkuItemSaleAttrVo> saleAttrVos =
                  skuSaleAttrValueService.listSaleAttrs(info.getSpuId());
              skuItemVo.setSaleAttr(saleAttrVos);
            },
            executor);

    // 4、获取spu的介绍-> 依赖1 获取spuId
    CompletableFuture<Void> descFuture =
        infoFuture.thenAcceptAsync(
            (info) -> {
              SpuInfoDescEntity byId = spuInfoDescService.getById(info.getSpuId());
              skuItemVo.setDesc(byId);
            },
            executor);

    // 5、获取spu的规格参数信息-> 依赖1 获取spuId catalogId
    CompletableFuture<Void> attrFuture =
        infoFuture.thenAcceptAsync(
            (info) -> {
              List<SpuItemAttrGroupVo> spuItemAttrGroupVos =
                  productAttrValueService.getProductGroupAttrsBySpuId(
                      info.getSpuId(), info.getCatelogId());
              skuItemVo.setGroupAttrs(spuItemAttrGroupVos);
            },
            executor);
    // 6、秒杀商品的优惠信息
//    CompletableFuture<Void> seckFuture =
//        CompletableFuture.runAsync(
//            () -> {
//              R r = seckillFeignService.getSeckillSkuInfo(skuId);
//              if (Integer.parseInt((String) r.get("code")) == 0) {
//                SeckillSkuVo seckillSkuVo = r.getData(new TypeReference<SeckillSkuVo>() {});
//                long current = System.currentTimeMillis();
//                // 如果返回结果不为空且活动未过期，设置秒杀信息
//                if (seckillSkuVo != null && current < seckillSkuVo.getEndTime()) {
//                  skuItemVo.setSeckillSkuVo(seckillSkuVo);
//                }
//              }
//            },
//            executor);

    // 等待所有任务执行完成
    try {
      CompletableFuture.allOf(imageFuture, saleFuture, descFuture, attrFuture).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
      return skuItemVo;
  }

  @Override
  public PageUtils queryPage(@NotNull Map<String, Object> params) {
    QueryWrapper<SkuInfoEntity> spuInfoEntityQueryWrapper = new QueryWrapper<>();
    String key = (String) params.get("key");
    String catelogId = (String) params.get("catelogId");
    String brandId = (String) params.get("brandId");
    if (StringUtils.isNotEmpty(key)) {
      spuInfoEntityQueryWrapper
          .like("sku_name", key)
          .or()
          .like("sku_title", key)
          .or()
          .like("sku_subtitle", key);
    }
    if (StringUtils.isNotEmpty(catelogId)) {
      long cid = Long.parseLong(catelogId);
      if (cid > 0) {
        spuInfoEntityQueryWrapper.eq("catelog_id", cid);
      }
    }
    if (StringUtils.isNotEmpty(brandId)) {
      long bid = Long.parseLong(brandId);
      if (bid > 0) {
        spuInfoEntityQueryWrapper.eq("brand_id", bid);
      }
    }
    String min = (String) params.get("min");
    String max = (String) params.get("max");
    if (StringUtils.isEmpty(min)) {
      min = "0";
    }
    if (StringUtils.isEmpty(max)) {
      max = "999999999";
    }
    log.debug(min + ":" + max);
    spuInfoEntityQueryWrapper.between("price", min, max);
    IPage<SkuInfoEntity> page =
        this.page(new Query<SkuInfoEntity>().getPage(params), spuInfoEntityQueryWrapper);
    return new PageUtils(page);
  }
}
