package cloud.stackexplode.gulimall.sms.to;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.swing.*;

/**
 * 短信请求
 *
 * @author 26530
 * @date 2022/10/17
 */
@Data
@Accessors(chain = true)
public class SmsRequestTo {
    /**
     * 商人id
     */
    String merchantId;
    /**
     * 电话号码
     */
    String phoneNumber;
    /**
     * 要求没有
     */
    String reqNo;
    /**
     * 短信标志标识
     */
    String smsSignId;
    /**
     * 短信模板不
     */
    String smsTemplateNo;
    /**
     * 验证代码
     */
    String verifyCode;

}
