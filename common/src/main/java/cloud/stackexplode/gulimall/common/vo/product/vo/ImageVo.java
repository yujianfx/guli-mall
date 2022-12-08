package cloud.stackexplode.gulimall.common.vo.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The type Image vo.
 */
@Data
@Accessors(chain = true)
public class ImageVo {

  private Integer defaultImg;

  private String imgUrl;
}
