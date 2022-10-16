package cloud.stackexplode.gulimall.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuEsTo {
  private Long skuId;
  private Long spuId;
  private String skuTitle;
  private BigDecimal skuPrice;
  private String skuImg;
  private Long saleCount;
  private Boolean hasStock;
  private Long hotScore;
  private Long brandId;
  private Long catalogId;
  private String brandName;
  private String brandImg;
  private String catelogName;
  private List<AttrTo> attrs;

  @Data
  public static class AttrTo {
    private Long attrId;
    private String attrName;
    private String attrValue;
  }
}
