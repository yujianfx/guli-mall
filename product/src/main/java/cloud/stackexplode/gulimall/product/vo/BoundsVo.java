package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class BoundsVo {

  private BigDecimal buyBounds;

  private BigDecimal growBounds;
}
