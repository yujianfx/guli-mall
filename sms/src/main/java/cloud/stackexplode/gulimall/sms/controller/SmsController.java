package cloud.stackexplode.gulimall.sms.controller;

import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @PostMapping("/sendSms")
    public R sendSms(@RequestBody AuthCodeRequest authCodeRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        smsService.sendSms(authCodeRequest.getTarget(), authCodeRequest.getCode());
        return R.ok("短信发送成功");
    }
}
