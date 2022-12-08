package cloud.stackexplode.gulimall.common.to.session;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = false, chain = true)
public class MemberSessionTo {
    private Long id;
    /**
     * 会员等级id
     */
    private Long levelId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;

    private Integer integration;
}
