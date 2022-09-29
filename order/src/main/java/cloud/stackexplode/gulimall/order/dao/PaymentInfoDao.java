package cloud.stackexplode.gulimall.order.dao;

import cloud.stackexplode.gulimall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
}
