package cloud.stackexplode.gulimall.ware.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
