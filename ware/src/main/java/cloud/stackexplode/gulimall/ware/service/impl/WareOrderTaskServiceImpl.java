package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.WareOrderTaskDao;
import cloud.stackexplode.gulimall.common.entities.ware.entity.WareOrderTaskEntity;
import cloud.stackexplode.gulimall.ware.service.WareOrderTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity>
    implements WareOrderTaskService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String wareId = (String) params.get("wareId");
    String key = (String) params.get("key");
    QueryWrapper<WareOrderTaskEntity> wareOrderTaskEntityQueryWrapper = new QueryWrapper<>();
    wareOrderTaskEntityQueryWrapper
        .eq(StringUtils.isNotEmpty(wareId), "ware_id", wareId)
        .and(
            StringUtils.isNotEmpty(key),
            (q) -> {
              q.like("sku_name", key);
            });
    IPage<WareOrderTaskEntity> page =
        this.page(
            new Query<WareOrderTaskEntity>().getPage(params), wareOrderTaskEntityQueryWrapper);

    return new PageUtils(page);
  }
}
