package cloud.stackexplode.gulimall.common.vo.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * The type Bounds vo.
 */
@Data
@Accessors(chain = true)
public class BoundsVo {

  private BigDecimal buyBounds;

  private BigDecimal growBounds;
}
