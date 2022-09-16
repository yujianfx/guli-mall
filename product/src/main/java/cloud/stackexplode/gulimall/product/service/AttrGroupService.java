package cloud.stackexplode.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
