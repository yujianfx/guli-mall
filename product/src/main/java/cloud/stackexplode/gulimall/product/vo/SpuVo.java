package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class SpuVo {
  private List<BaseAttrVo> baseAttrs;

  private BoundsVo bounds;

  private Long brandId;

  private Long catelogId;

  private List<String> decript;

  private List<String> images;

  private Long publishStatus;

  private List<SkuVo> skus;

  private String spuDescription;

  private String spuName;

  private BigDecimal weight;
}
