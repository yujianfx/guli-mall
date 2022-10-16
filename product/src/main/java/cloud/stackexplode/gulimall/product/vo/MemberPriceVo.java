package cloud.stackexplode.gulimall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * The type Member price vo.
 */
@Data
@Accessors(chain = true)
public class MemberPriceVo {

  private Long id;

  private String name;

  private BigDecimal price;
}
