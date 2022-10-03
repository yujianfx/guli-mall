package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.to.SkuReductionTo;
import cloud.stackexplode.gulimall.common.to.SpuBoundTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.SpuInfoDao;
import cloud.stackexplode.gulimall.product.entity.*;
import cloud.stackexplode.gulimall.product.feign.CouponFeignService;
import cloud.stackexplode.gulimall.product.service.*;
import cloud.stackexplode.gulimall.product.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity>
    implements SpuInfoService {
  @Autowired private SpuImagesService spuImagesService;
  @Autowired private SkuImagesService skuImagesService;
  @Autowired private ProductAttrValueService productAttrValueService;
  @Autowired private SkuInfoService skuInfoService;
  @Autowired private SkuSaleAttrValueService skuSaleAttrValueService;
  @Autowired private SpuInfoDescService spuInfoDescService;
  @Autowired private AttrService attrService;
  @Autowired private CouponFeignService couponFeignService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Boolean saveSpuDetail(SpuVo spuVo) throws Exception {
    boolean res = true;
    try {
      SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
      BeanUtils.copyProperties(spuVo, spuInfoEntity);
      spuInfoEntity.setCreateTime(new Date()).setUpdateTime(new Date());
      this.save(spuInfoEntity);
      Long spuId = spuInfoEntity.getId();
      List<String> describe = spuVo.getDecript();
      SpuInfoDescEntity spuInfoDescEntity =
          new SpuInfoDescEntity().setSpuId(spuId).setDecript(String.join(",", describe));
      spuInfoDescService.save(spuInfoDescEntity);
      SpuBoundTo spuBoundTo = new SpuBoundTo();
      BeanUtils.copyProperties(spuVo.getBounds(), spuBoundTo);
      spuBoundTo.setSpuId(spuId);
      couponFeignService.saveBounds(spuBoundTo);
      List<String> imgs = spuVo.getImages();
      imgs.forEach(
          (img -> {
            SpuImagesEntity spuImagesEntity = new SpuImagesEntity().setImgUrl(img).setSpuId(spuId);
            spuImagesService.save(spuImagesEntity);
          }));
      List<BaseAttrVo> baseAttrVos = spuVo.getBaseAttrs();
      List<ProductAttrValueEntity> productAttrValueEntities =
          baseAttrVos.stream()
              .map(
                  (vo) -> {
                    Long attrId = vo.getAttrId();
                    AttrEntity attrEntity = attrService.getById(attrId);
                    return new ProductAttrValueEntity()
                        .setAttrId(attrId)
                        .setAttrValue(vo.getAttrValues())
                        .setAttrName(attrEntity.getAttrName())
                        .setSpuId(spuId)
                        .setQuickShow(vo.getShowDesc());
                  })
              .collect(Collectors.toList());
      productAttrValueService.saveBatch(productAttrValueEntities);
      List<SkuVo> skuVos = spuVo.getSkus();
      skuVos.forEach(
          skuVo -> {
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            BeanUtils.copyProperties(skuVo, skuInfoEntity);
            List<String> defaultImgs =
                skuVo.getImages().stream()
                    .filter(imageVo -> imageVo.getDefaultImg() == 0)
                    .map(ImageVo::getImgUrl)
                    .collect(Collectors.toList());
            skuInfoEntity
                .setSpuId(spuId)
                .setBrandId(spuVo.getBrandId())
                .setCatalogId(spuVo.getCatalogId())
                .setSkuDefaultImg(defaultImgs.size() > 0 ? defaultImgs.get(0) : "");
            skuInfoService.save(skuInfoEntity);
            Long skuId = skuInfoEntity.getSkuId();
            List<ImageVo> skuVoImages = skuVo.getImages();
            List<SkuImagesEntity> skuImagesEntities =
                skuVoImages.stream()
                    .filter((s) -> StringUtils.isNotEmpty(s.getImgUrl()))
                    .map(
                        imageVo -> {
                          SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                          BeanUtils.copyProperties(imageVo, skuImagesEntity);
                          skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                          return skuImagesEntity;
                        })
                    .collect(Collectors.toList());
            skuImagesService.saveBatch(skuImagesEntities);
            List<SkuAttrVo> skuAttrVos = skuVo.getAttr();
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities =
                skuAttrVos.stream()
                    .map(
                        skuAttrVo -> {
                          SkuSaleAttrValueEntity skuSaleAttrValueEntity =
                              new SkuSaleAttrValueEntity();
                          BeanUtils.copyProperties(skuAttrVo, skuSaleAttrValueEntity);
                          skuSaleAttrValueEntity.setSkuId(skuId);
                          return skuSaleAttrValueEntity;
                        })
                    .collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
            SkuReductionTo skuReductionTo = new SkuReductionTo();
            BeanUtils.copyProperties(skuVo, skuReductionTo);
            skuReductionTo.setSkuId(skuId);
          });
    } catch (Exception e) {
      res = false;
      log.error("添加spu信息失败");
      log.error(e.toString());
      throw new Exception("spu添加失败");
    } finally {
      log.debug("添加spu信息结束");
    }
    return res;
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    QueryWrapper<SpuInfoEntity> spuInfoEntityQueryWrapper = new QueryWrapper<>();
    String key = (String) params.get("key");
    Long catelogId = (Long) params.get("catelogId");
    Long brandId = (Long) params.get("brandId");
    String min = (String) params.get("min");
    String max = (String) params.get("max");

    if (StringUtils.isNotEmpty(key)) {
      spuInfoEntityQueryWrapper.like("spu_name", key).or().like("spu_description", key);
    }

    IPage<SpuInfoEntity> page =
        this.page(new Query<SpuInfoEntity>().getPage(params), spuInfoEntityQueryWrapper);

    return new PageUtils(page);
  }
}
