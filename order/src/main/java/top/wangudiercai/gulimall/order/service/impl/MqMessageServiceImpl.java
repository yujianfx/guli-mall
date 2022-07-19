package top.wangudiercai.gulimall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.order.dao.MqMessageDao;
import top.wangudiercai.gulimall.order.entity.MqMessageEntity;
import top.wangudiercai.gulimall.order.service.MqMessageService;

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
