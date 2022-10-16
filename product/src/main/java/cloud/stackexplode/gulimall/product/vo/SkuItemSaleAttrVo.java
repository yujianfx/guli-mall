package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/** The type Sku item sale attr vo. */
@Data
@ToString
public class SkuItemSaleAttrVo {

  private Long attrId;

  private String attrName;

  private List<AttrValueWithSkuIdVo> attrValues;
}
