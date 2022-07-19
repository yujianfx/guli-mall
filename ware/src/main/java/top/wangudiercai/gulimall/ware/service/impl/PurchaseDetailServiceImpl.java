package top.wangudiercai.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.ware.dao.PurchaseDetailDao;
import top.wangudiercai.gulimall.ware.entity.PurchaseDetailEntity;
import top.wangudiercai.gulimall.ware.service.PurchaseDetailService;

@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity>
    implements PurchaseDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<PurchaseDetailEntity> page =
        this.page(
            new Query<PurchaseDetailEntity>().getPage(params),
            new QueryWrapper<PurchaseDetailEntity>());

    return new PageUtils(page);
  }
}
