package cloud.stackexplode.gulimall.product.service;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.product.entity.AttrEntity;
import cloud.stackexplode.gulimall.product.entity.ProductAttrValueEntity;
import cloud.stackexplode.gulimall.product.vo.AttrRespVo;
import cloud.stackexplode.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface AttrService extends IService<AttrEntity> {

  PageUtils queryPage(Map<String, Object> params);

  boolean saveAttr(AttrVo attrVo);

  PageUtils queryPageByCid(Map<String, Object> params, Integer attrType, Long cid);

  AttrRespVo getAttrDetailById(Integer attrType, Long attrId);

  List<ProductAttrValueEntity> queryPageBySpuId(Long sId);
}
