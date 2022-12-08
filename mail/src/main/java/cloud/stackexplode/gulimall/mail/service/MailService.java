package cloud.stackexplode.gulimall.mail.service;

import cloud.stackexplode.gulimall.common.to.mail.EmailWarnTo;

import javax.validation.Valid;

public interface MailService {
    void sendMail(String target, String code) throws Exception;

    void sendWarnMail(@Valid EmailWarnTo emailWarnTo) throws Exception;
}
