package cloud.stackexplode.gulimall.common.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class AuthCodeRequest {
    private String target;
    private String code;
    private LocalDateTime requestTime;

}
