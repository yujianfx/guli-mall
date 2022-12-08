package cloud.stackexplode.gulimall.common.vo.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/** The type Catelog 2 vo. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Catelog2Vo {
  // 父分类id
  private String catelog1Id;

  private String id;

  private String name;

  private List<Catelog3Vo> catelog3List;

  /** The type Catelog 3 vo. */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Catelog3Vo {
    private String catelog2Id;
    private String id;
    private String name;
  }
}
