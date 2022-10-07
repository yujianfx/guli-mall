package cloud.stackexplode.gulimall.product.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SpuRespVo {
  private static final long serialVersionUID = 1L;

  /** 商品id */
  @TableId(type = IdType.AUTO)
  private Long id;
  /** 商品名称 */
  private String spuName;
  /** 商品描述 */
  private String spuDescription;
  /** 所属分类id */
  private Long catelogId;
  /** 品牌id */
  private Long brandId;
  /** */
  private BigDecimal weight;
  /** 上架状态[0 - 下架，1 - 上架] */
  private Integer publishStatus;
  /** */
  private Date createTime;
  /** */
  private Date updateTime;

  private Integer showStatus;
}
