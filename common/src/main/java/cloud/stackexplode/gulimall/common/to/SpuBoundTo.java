package cloud.stackexplode.gulimall.common.to;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author 26530
 */
@Data
@Accessors(chain = true)
public class SpuBoundTo {
  private Long spuId;
  private BigDecimal buyBounds;
  private BigDecimal growBounds;
}