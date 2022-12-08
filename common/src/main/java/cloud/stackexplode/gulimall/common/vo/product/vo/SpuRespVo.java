package cloud.stackexplode.gulimall.common.vo.product.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Spu resp vo.
 */
@Data
@Accessors(chain = true)
public class SpuRespVo {
  private static final long serialVersionUID = 1L;

  @TableId(type = IdType.AUTO)
  private Long id;
  private String spuName;
  private String spuDescription;
  private Long catelogId;
  private Long brandId;
  private BigDecimal weight;
  private Integer publishStatus;
  private Date createTime;
  private Date updateTime;

  private Integer showStatus;
}
