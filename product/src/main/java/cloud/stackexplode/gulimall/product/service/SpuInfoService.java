package cloud.stackexplode.gulimall.product.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.entity.SpuInfoEntity;
import cloud.stackexplode.gulimall.product.vo.SpuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**spu信息服务
 * spu信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

  /**
   * 查询页面
   *
   * @param params 参数个数
   * @return {@link PageUtils}
   */PageUtils queryPage(Map<String, Object> params);

  /**
   * 保存spu细节
   *
   * @param spuVo spu签证官
   * @return {@link Boolean}
   * @throws Exception 异常
   */Boolean saveSpuDetail(SpuVo spuVo) throws Exception;

  /**
   * 更新基本attrs spu id
   *
   * @param productAttrValueEntity 产品attr价值实体
   * @return {@link Boolean}
   */Boolean updateBaseAttrsBySpuId(List<ProductAttrValueEntity> productAttrValueEntity);
}
