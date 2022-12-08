package cloud.stackexplode.gulimall.common.entities.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员统计信息
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
@Data @Accessors(fluent = false, chain = true)
@TableName("ums_member_statistics_info")
public class MemberStatisticsInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 累计消费金额
     */
    private BigDecimal consumeAmount;
    /**
     * 累计优惠金额
     */
    private BigDecimal couponAmount;
    /**
     * 订单数量
     */
    private Integer orderCount;
    /**
     * 优惠券数量
     */
    private Integer couponCount;
    /**
     * 评价数
     */
    private Integer commentCount;
    /**
     * 退货数量
     */
    private Integer returnOrderCount;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 关注数量
     */
    private Integer attendCount;
    /**
     * 粉丝数量
     */
    private Integer fansCount;
    /**
     * 收藏的商品数量
     */
    private Integer collectProductCount;
    /**
     * 收藏的专题活动数量
     */
    private Integer collectSubjectCount;
    /**
     * 收藏的评论数量
     */
    private Integer collectCommentCount;
    /**
     * 邀请的朋友数量
     */
    private Integer inviteFriendCount;
}
