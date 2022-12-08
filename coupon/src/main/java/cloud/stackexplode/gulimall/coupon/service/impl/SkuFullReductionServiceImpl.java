package cloud.stackexplode.gulimall.coupon.service.impl;

import cloud.stackexplode.gulimall.common.to.product.SkuReductionTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.coupon.dao.SkuFullReductionDao;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.MemberPriceEntity;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.SkuFullReductionEntity;
import cloud.stackexplode.gulimall.common.entities.coupon.entity.SkuLadderEntity;
import cloud.stackexplode.gulimall.coupon.service.MemberPriceService;
import cloud.stackexplode.gulimall.coupon.service.SkuFullReductionService;
import cloud.stackexplode.gulimall.coupon.service.SkuLadderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl
    extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity>
    implements SkuFullReductionService {
  @Autowired private SkuLadderService skuLadderService;
  @Autowired private MemberPriceService memberPriceService;

  @Override
  public Boolean saveSkuReductionTo(SkuReductionTo skuReductionTo) {
    SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
    BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
    this.save(skuFullReductionEntity);
    SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
    BeanUtils.copyProperties(skuReductionTo, skuLadderEntity);
    Boolean sl = skuLadderService.save(skuLadderEntity);
    assert skuReductionTo.getMemberPrice() != null : "会员优惠信息为空！！！";
    List<MemberPriceEntity> memberPriceEntities =
        skuReductionTo.getMemberPrice().stream()
            .map(
                memberPriceTo -> {
                  MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                  return memberPriceEntity
                      .setMemberPrice(memberPriceTo.getPrice())
                      .setMemberLevelId(memberPriceTo.getId())
                      .setMemberLevelName(memberPriceTo.getName())
                      .setSkuId(skuReductionTo.getSkuId());
                })
            .collect(Collectors.toList());
    Boolean mp = memberPriceService.saveBatch(memberPriceEntities);
    return sl & mp;
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuFullReductionEntity> page =
        this.page(
            new Query<SkuFullReductionEntity>().getPage(params),
            new QueryWrapper<SkuFullReductionEntity>());

    return new PageUtils(page);
  }
}
