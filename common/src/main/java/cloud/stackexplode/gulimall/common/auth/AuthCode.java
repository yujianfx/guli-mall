package cloud.stackexplode.gulimall.common.auth;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Data
@Accessors(chain = true)
public class AuthCode {
    @Length(min = 6, max = 6)
    private String code;
    private LocalDateTime expireTime;
    private AuthCodeType type;

}
