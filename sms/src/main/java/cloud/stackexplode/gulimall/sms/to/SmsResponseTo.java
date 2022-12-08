
package cloud.stackexplode.gulimall.sms.to;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SmsResponseTo {

    private String code;

    private String message;

    private String reqNo;

    private String verificationCode;

}
