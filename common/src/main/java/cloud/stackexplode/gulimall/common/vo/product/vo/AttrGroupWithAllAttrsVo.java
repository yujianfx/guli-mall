package cloud.stackexplode.gulimall.common.vo.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The type Attr group with all attrs vo.
 */
@Data
@Accessors(chain = true)
public class AttrGroupWithAllAttrsVo {
  private static final long serialVersionUID = 1L;

  private Long attrGroupId;
  private String attrGroupName;
  private Integer sort;
  private String descript;
  private String icon;
  private Long catelogId;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<AttrRespVo> attrRespVos;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long[] catelogPath;
}
