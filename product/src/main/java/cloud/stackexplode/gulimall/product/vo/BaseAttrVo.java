package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseAttrVo {

  private Long attrId;

  private String attrValues;

  private Integer showDesc;
}
