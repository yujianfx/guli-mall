package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The type Sku attr vo.
 */
@Data
@Accessors(chain = true)
public class SkuAttrVo {

  private Long attrId;

  private String attrName;

  private String attrValue;
}
