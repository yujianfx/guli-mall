package cloud.stackexplode.gulimall.ware.service;

import cloud.stackexplode.gulimall.common.entities.ware.entity.WareOrderTaskDetailEntity;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 库存工作单
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {
    List<WareOrderTaskDetailEntity> getWareOrderTaskDetailByTaskId(Long id);

    PageUtils queryPage(Map<String, Object> params);
}
