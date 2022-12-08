package cloud.stackexplode.gulimall.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterVo {
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 19, message = "用户名长度在6—18字符")
    private String userName;

    @NotEmpty(message = "密码必须填写")
    @Length(min = 6, max = 19, message = "密码必须是6—18字符")
    private String passWord;

    @Email
    private String target;
    private String mobile;
    private String email;
    @NotEmpty(message = "验证码不能为空")
    private String code;

}
