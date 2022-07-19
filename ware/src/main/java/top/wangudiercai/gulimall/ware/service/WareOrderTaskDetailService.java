package top.wangudiercai.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
