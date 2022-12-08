package cloud.stackexplode.gulimall.ware.dao;

import cloud.stackexplode.gulimall.common.entities.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:57:36
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    void addstock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    Integer getTotalStock(@Param("id") Long id);

    List<Long> listWareIdsHasStock(@Param("skuId") Long skuId, @Param("count") Integer count);

    Long lockWareSku(@Param("skuId") Long skuId, @Param("num") Integer num, @Param("wareId") Long wareId);

    void unlockStock(@Param("skuId") Long skuId, @Param("skuNum") Integer skuNum, @Param("wareId") Long wareId);
}
