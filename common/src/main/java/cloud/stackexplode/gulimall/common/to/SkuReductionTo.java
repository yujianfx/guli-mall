package cloud.stackexplode.gulimall.common.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Sku reduction to.
 * @author 26530
 */
@Data
@Accessors(chain = true)
public class SkuReductionTo {
  private Long skuId;
  private Integer fullCount;
  private BigDecimal discount;
  private Integer countStatus;
  private BigDecimal fullPrice;
  private BigDecimal reducePrice;
  private Integer priceStatus;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<MemberPriceTo> memberPrice;
}
