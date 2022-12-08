package cloud.stackexplode.gulimall.common.vo.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** The type Attr resp vo. */
@Data
@Accessors(fluent = false, chain = true)
@EqualsAndHashCode(callSuper = false)
public class AttrRespVo extends AttrVo {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long[] catelogPath;

  private String catelogName;

  private String groupName;
}
