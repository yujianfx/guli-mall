package cloud.stackexplode.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.order.dao.RefundInfoDao;
import cloud.stackexplode.gulimall.order.entity.RefundInfoEntity;
import cloud.stackexplode.gulimall.order.service.RefundInfoService;

@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity>
    implements RefundInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<RefundInfoEntity> page =
        this.page(
            new Query<RefundInfoEntity>().getPage(params), new QueryWrapper<RefundInfoEntity>());

    return new PageUtils(page);
  }
}
