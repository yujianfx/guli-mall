package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.constant.WareConstant;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.PurchaseDao;
import cloud.stackexplode.gulimall.ware.entity.PurchaseDetailEntity;
import cloud.stackexplode.gulimall.ware.entity.PurchaseEntity;
import cloud.stackexplode.gulimall.ware.service.PurchaseDetailService;
import cloud.stackexplode.gulimall.ware.service.PurchaseService;
import cloud.stackexplode.gulimall.ware.vo.PurchaseDetailMergeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity>
    implements PurchaseService {
  @Autowired private PurchaseDetailService purchaseDetailService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Boolean mergeDetails(@NotNull PurchaseDetailMergeVo purchase) {
    boolean res = true;
    try {
      Long purchaseId = purchase.getPurchaseId();
      if (purchaseId != null) {
        purchase
            .getPurchaseDetailIds()
            .forEach(
                (ids) -> {
                  UpdateWrapper<PurchaseDetailEntity> updateWrapper =
                      new UpdateWrapper<PurchaseDetailEntity>()
                          .eq("id", ids)
                          .set(purchaseId > 0, "purchase_id", purchaseId);
                  purchaseDetailService.update(updateWrapper);
                });
      } else {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity
            .setCreateTime(new Date())
            .setUpdateTime(new Date())
            .setStatus(WareConstant.PurchaseStatusEnum.CREATED);
        this.save(purchaseEntity);
        purchase
            .getPurchaseDetailIds()
            .forEach(
                (ids) -> {
                  UpdateWrapper<PurchaseDetailEntity> updateWrapper =
                      new UpdateWrapper<PurchaseDetailEntity>()
                          .eq("id", ids)
                          .set(purchaseEntity.getId() > 0, "purchase_id", purchaseEntity.getId());
                  purchaseDetailService.update(updateWrapper);
                });
      }
    } catch (Exception e) {
      res = false;
      log.error("合并失败" + e.getMessage());
      e.printStackTrace();
      throw e;
    } finally {
      log.info("合并结束");
    }

    return res;
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String key = (String) params.get("key");
    String status = (String) params.get("status");
    QueryWrapper<PurchaseEntity> purchaseEntityQueryWrapper = new QueryWrapper<>();
    purchaseEntityQueryWrapper
        .like(StringUtils.isNotEmpty(key), "assignee_name", key)
        .eq(StringUtils.isNotEmpty(status), "status", status);
    IPage<PurchaseEntity> page =
        this.page(new Query<PurchaseEntity>().getPage(params), purchaseEntityQueryWrapper);

    return new PageUtils(page);
  }
}
