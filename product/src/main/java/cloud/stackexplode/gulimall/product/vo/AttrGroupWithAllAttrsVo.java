package cloud.stackexplode.gulimall.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AttrGroupWithAllAttrsVo {
  private static final long serialVersionUID = 1L;

  /** 分组id */
  private Long attrGroupId;
  /** 组名 */
  private String attrGroupName;
  /** 排序 */
  private Integer sort;
  /** 描述 */
  private String descript;
  /** 组图标 */
  private String icon;
  /** 所属分类id */
  private Long catelogId;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<AttrRespVo> attrRespVos;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long[] catelogPath;
}
