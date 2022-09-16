package cloud.stackexplode.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;

import cloud.stackexplode.gulimall.member.dao.IntegrationChangeHistoryDao;
import cloud.stackexplode.gulimall.member.entity.IntegrationChangeHistoryEntity;
import cloud.stackexplode.gulimall.member.service.IntegrationChangeHistoryService;

@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl
    extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity>
    implements IntegrationChangeHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<IntegrationChangeHistoryEntity> page =
        this.page(
            new Query<IntegrationChangeHistoryEntity>().getPage(params),
            new QueryWrapper<IntegrationChangeHistoryEntity>());

    return new PageUtils(page);
  }
}
