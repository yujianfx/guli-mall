package cloud.stackexplode.gulimall.common.to;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class MemberPriceTo {

  private Long id;

  private String name;

  private BigDecimal price;
}
