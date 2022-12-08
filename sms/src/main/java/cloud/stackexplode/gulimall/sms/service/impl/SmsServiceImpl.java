package cloud.stackexplode.gulimall.sms.service.impl;

import cloud.stackexplode.gulimall.sms.feign.SmsFeignService;
import cloud.stackexplode.gulimall.sms.service.SmsService;
import cloud.stackexplode.gulimall.sms.to.SmsRequestTo;
import cloud.stackexplode.gulimall.sms.to.SmsResponseTo;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Value("${sms.secretId}")
    private String secretId;
    @Value("${sms.secretKey}")
    private String secretKey;
    private static final String SOURCE = "market";


    private String getAuthString(String localDateTime) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String signStr = "x-date: " + localDateTime + "\n" + "x-source: " + SOURCE;
        Mac mac = Mac.getInstance("HmacSHA1");
        Key sKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(sKey);
        byte[] hash = mac.doFinal(signStr.getBytes(StandardCharsets.UTF_8));
        String sig = new BASE64Encoder().encode(hash);
        String auth = "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"" + sig + "\"";
        return auth;
    }

    private Map<String, String> smsFeignHttpHeaders() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        String auth = getAuthString(datetime);
        Map<String, String> smsFeignHttpHeaders = new HashMap<>(4);
        smsFeignHttpHeaders.put("X-Source", "market");
        smsFeignHttpHeaders.put("X-Date", datetime);
        smsFeignHttpHeaders.put("Authorization", auth);
        smsFeignHttpHeaders.put("Content-Type", "application/x-www-form-urlencoded");

        return smsFeignHttpHeaders;

    }

    @Autowired
    private SmsFeignService smsFeignService;

    @Async
    @Override
    public void sendSms(String phone, String code) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        log.info("目标:{},验证码:{}", phone, code);
        SmsRequestTo smsRequestTo = new SmsRequestTo()
                .setSmsSignId("0000")
                .setMerchantId("market-1pim1kl7a")
                .setSmsTemplateNo("0001")
                .setReqNo(UUID.fastUUID().toString())
                .setPhoneNumber(phone)
                .setVerifyCode(code);
        SmsResponseTo smsResponseTo = smsFeignService.sendSms(smsRequestTo, smsFeignHttpHeaders());
        log.info("smsResponseTo: {}", smsResponseTo);
    }
}

