package cloud.stackexplode.gulimall.common.to.member;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * The type Member price to.
 */
@Data
@Accessors(chain = true)
public class MemberPriceTo {

  private Long id;

  private String name;

  private BigDecimal price;
}
