package cloud.stackexplode.gulimall.ware.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 采购信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
