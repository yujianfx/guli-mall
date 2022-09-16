package cloud.stackexplode.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
