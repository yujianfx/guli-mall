package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.ware.dao.WareOrderTaskDao;
import cloud.stackexplode.gulimall.ware.entity.WareOrderTaskEntity;
import cloud.stackexplode.gulimall.ware.service.WareOrderTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity>
        implements WareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page =
                this.page(
                        new Query<WareOrderTaskEntity>().getPage(params),
                        new QueryWrapper<WareOrderTaskEntity>());

        return new PageUtils(page);
    }
}
