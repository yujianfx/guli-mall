package cloud.stackexplode.gulimall.auth.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private Long id;
    private String userName;
    private String passWord;
}
