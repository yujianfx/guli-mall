package top.wangudiercai.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.ware.dao.WareOrderTaskDetailDao;
import top.wangudiercai.gulimall.ware.entity.WareOrderTaskDetailEntity;
import top.wangudiercai.gulimall.ware.service.WareOrderTaskDetailService;

@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl
    extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity>
    implements WareOrderTaskDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareOrderTaskDetailEntity> page =
        this.page(
            new Query<WareOrderTaskDetailEntity>().getPage(params),
            new QueryWrapper<WareOrderTaskDetailEntity>());

    return new PageUtils(page);
  }
}
