package cloud.stackexplode.gulimall.product.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * spu图片
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@Data
@TableName("pms_spu_images")
public class SpuImagesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * spu_id
     */
    private Long spuId;
    /**
     * 图片名
     */
    private String imgName;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 顺序
     */
    private Integer imgSort;
    /**
     * 是否默认图
     */
    private Integer defaultImg;

    @TableLogic
    private Integer showStatus;
}
