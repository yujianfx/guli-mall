package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.constant.SpuConstant;
import cloud.stackexplode.gulimall.common.entities.product.entity.*;
import cloud.stackexplode.gulimall.common.to.es.SkuEsTo;
import cloud.stackexplode.gulimall.common.to.product.SkuHasStockVo;
import cloud.stackexplode.gulimall.common.to.product.SkuReductionTo;
import cloud.stackexplode.gulimall.common.to.product.SpuBoundTo;
import cloud.stackexplode.gulimall.common.to.ware.WareSkuTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.utils.StatusCode;
import cloud.stackexplode.gulimall.common.vo.product.vo.BaseAttrVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.ImageVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.SkuAttrVo;
import cloud.stackexplode.gulimall.common.vo.product.vo.SpuVo;
import cloud.stackexplode.gulimall.product.dao.SpuInfoDao;
import cloud.stackexplode.gulimall.product.feign.CouponFeignService;
import cloud.stackexplode.gulimall.product.feign.SearchFeignService;
import cloud.stackexplode.gulimall.product.feign.WareFeignService;
import cloud.stackexplode.gulimall.product.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    @Autowired
    private SpuImagesService spuImagesService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private CouponFeignService couponFeignService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchFeignService searchFeignService;

    @Override
    public SpuInfoEntity getSpuBySkuId(Long skuId) {
        SkuInfoEntity skuInfoEntity = skuInfoService.getById(skuId);
        Long spuId = skuInfoEntity.getSpuId();
        return getById(spuId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveSpuDetail(SpuVo spuVo) throws Exception {
        boolean res = true;
        try {
            SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
            BeanUtils.copyProperties(spuVo, spuInfoEntity);
            spuInfoEntity
                    .setCreateTime(new Date())
                    .setUpdateTime(new Date()).setPublishStatus(SpuConstant.SpuStatus.CREATE);
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
            List<SkuInfoEntity> infoEntities =
                    spuVo.getSkus().stream()
                            .map(
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
                                                .setCatelogId(spuVo.getCatelogId())
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
                                        return skuInfoEntity;
                                    })
                            .collect(Collectors.toList());
            // 远程调用保存默认的库存信息
            List<WareSkuTo> wareSkuTos =
                    infoEntities.stream()
                            .map(
                                    skuInfoEntity -> {
                                        WareSkuTo wareSkuTo = new WareSkuTo();
                                        BeanUtils.copyProperties(skuInfoEntity, wareSkuTo);
                                        wareSkuTo
                                                .setWareId(1L)
                                                .setStock(0)
                                                .setStockLocked(0)
                                                .setSkuId(skuInfoEntity.getSkuId());
                                        return wareSkuTo;
                                    })
                            .collect(Collectors.toList());
            wareFeignService.saveSkuWare(wareSkuTos);
        } catch (Exception e) {
            res = false;
            log.error("添加spu信息失败");
            log.error(e.getMessage());
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
        String catelogId = (String) params.get("catelogId");
        String brandId = (String) params.get("brandId");
        String status = (String) params.get("status");
        spuInfoEntityQueryWrapper
                .like(StringUtils.isNotEmpty(key), "spu_name", key)
                .or()
                .like("spu_description", key)
                .eq(
                        StringUtils.isNotEmpty(catelogId),
                        "catelog_id",
                        Long.valueOf(catelogId == null ? "0" : catelogId))
                .eq(
                        StringUtils.isNotEmpty(brandId),
                        "brand_id",
                        Long.valueOf(brandId == null ? "0" : brandId))
                .eq(StringUtils.isNotEmpty(status), "publish_status", status);
        IPage<SpuInfoEntity> page =
                this.page(new Query<SpuInfoEntity>().getPage(params), spuInfoEntityQueryWrapper);
        return new PageUtils(page);
    }

    @Override
    public Boolean updateBaseAttrsBySpuId(List<ProductAttrValueEntity> productAttrValueEntity) {
        productAttrValueEntity.forEach(
                (p) -> {
                    productAttrValueService
                            .update()
                            .eq("spu_id", p.getSpuId())
                            .eq("attr_id", p.getAttrId())
                            .update(p);
                });
        return true;
    }

    @Override
    public void upSpuForSearch(Long spuId) {
        // 1、查出当前spuId对应的所有sku信息,品牌的名字
        List<SkuInfoEntity> skuInfoEntities =
                skuInfoService.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
        // TODO 4、查出当前sku的所有可以被用来检索的规格属性
        List<ProductAttrValueEntity> productAttrValueEntities =
                productAttrValueService.list(
                        new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        List<Long> searchIds =
                productAttrValueEntities.stream()
                        .map(ProductAttrValueEntity::getAttrId)
                        .collect(Collectors.toList());
        List<SkuEsTo.AttrTo> searchAttrs =
                productAttrValueEntities.stream()
                        .filter(
                                entity ->
                                        attrService
                                                .lambdaQuery()
                                                .eq(AttrEntity::getAttrId, entity.getAttrId())
                                                .eq(AttrEntity::getSearchType, 0)
                                                .count()
                                                > 0)
                        .map(
                                entity -> {
                                    SkuEsTo.AttrTo attr = new SkuEsTo.AttrTo();
                                    BeanUtils.copyProperties(entity, attr);
                                    return attr;
                                })
                        .collect(Collectors.toList());

        // TODO 1、发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            List<Long> longList =
                    skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
            List<SkuHasStockVo> skuHasStocks =
                    (List<SkuHasStockVo>) wareFeignService.getSkuHasStocks(longList).getData();
            stockMap =
                    skuHasStocks.stream()
                            .collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } catch (Exception e) {
            log.error("远程调用库存服务失败,原因{}", e);
        }

        try { // 2、封装每个sku的信息
            Map<Long, Boolean> finalStockMap = stockMap;
            List<SkuEsTo> skuEsModels =
                    skuInfoEntities.stream()
                            .map(
                                    sku -> {
                                        SkuEsTo skuEsModel = new SkuEsTo();
                                        BeanUtils.copyProperties(sku, skuEsModel);
                                        skuEsModel.setSkuPrice(sku.getPrice());
                                        skuEsModel.setSkuImg(sku.getSkuDefaultImg());
                                        // TODO 2、热度评分。0
                                        skuEsModel.setHotScore(0L);
                                        // TODO 3、查询品牌和分类的名字信息
                                        BrandEntity brandEntity = brandService.getById(sku.getBrandId());
                                        skuEsModel.setBrandName(brandEntity.getName());
                                        skuEsModel.setBrandImg(brandEntity.getLogo());
                                        CategoryEntity categoryEntity = categoryService.getById(sku.getCatelogId());
                                        skuEsModel.setCatelogName(categoryEntity.getName());
                                        // 设置可搜索属性
                                        skuEsModel.setAttrs(searchAttrs);
                                        // 设置是否有库存
                                        skuEsModel.setHasStock(
                                                finalStockMap != null && finalStockMap.get(sku.getSkuId()));
                                        return skuEsModel;
                                    })
                            .collect(Collectors.toList());
            R r = searchFeignService.productStatusUp(skuEsModels);
            if (r.getCode().equals(StatusCode.SUCCESS)) {
                this.baseMapper.update(
                        new SpuInfoEntity().setPublishStatus(SpuConstant.SpuStatus.SALEING),
                        new UpdateWrapper<SpuInfoEntity>().eq("id", spuId));
            } else {
                log.error("商品远程es保存失败");
            }
        } catch (Exception e) {
            log.error("商品远程es保存失败,原因{}", e);
            throw e;
        }
    }
}
