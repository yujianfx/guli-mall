package cloud.stackexplode.gulimall.common.to.mail;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@Accessors(chain = true)
public class EmailWarnTo {
    @Email
    private String target;
    @Length(min = 6, max = 36)
    private String subject;
    @Length(min = 6, max = 36)
    private String content;
}
