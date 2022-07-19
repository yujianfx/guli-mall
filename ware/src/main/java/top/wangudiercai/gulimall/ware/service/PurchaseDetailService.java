package top.wangudiercai.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
