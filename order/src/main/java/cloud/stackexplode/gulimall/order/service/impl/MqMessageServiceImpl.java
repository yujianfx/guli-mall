package cloud.stackexplode.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.order.dao.MqMessageDao;
import cloud.stackexplode.gulimall.order.entity.MqMessageEntity;
import cloud.stackexplode.gulimall.order.service.MqMessageService;

@Service("mqMessageService")
public class MqMessageServiceImpl extends ServiceImpl<MqMessageDao, MqMessageEntity>
    implements MqMessageService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MqMessageEntity> page =
        this.page(
            new Query<MqMessageEntity>().getPage(params), new QueryWrapper<MqMessageEntity>());

    return new PageUtils(page);
  }
}
