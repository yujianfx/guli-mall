package cloud.stackexplode.gulimall.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface SmsService {
    void sendSms(String phone, String code) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException;
}
