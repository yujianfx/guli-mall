package cloud.stackexplode.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.order.dao.PaymentInfoDao;
import cloud.stackexplode.gulimall.order.entity.PaymentInfoEntity;
import cloud.stackexplode.gulimall.order.service.PaymentInfoService;

@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity>
    implements PaymentInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<PaymentInfoEntity> page =
        this.page(
            new Query<PaymentInfoEntity>().getPage(params), new QueryWrapper<PaymentInfoEntity>());

    return new PageUtils(page);
  }
}
