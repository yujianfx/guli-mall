package cloud.stackexplode.gulimall.common.to.ware;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class WareSkuTo implements Serializable {
  private static final long serialVersionUID = 1L;
  /** sku_id */
  private Long skuId;
  /** 仓库id */
  private Long wareId;
  /** 库存数 */
  private Integer stock;
  /** sku_name */
  private String skuName;
  /** 锁定库存 */
  private Integer stockLocked;
}
