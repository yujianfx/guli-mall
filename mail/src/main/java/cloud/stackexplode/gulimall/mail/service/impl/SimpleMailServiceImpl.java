package cloud.stackexplode.gulimall.mail.service.impl;

import cloud.stackexplode.gulimall.common.to.mail.EmailWarnTo;
import cloud.stackexplode.gulimall.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.nlab.smtp.pool.SmtpConnectionPool;
import org.nlab.smtp.transport.connection.ClosableSmtpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

@Slf4j
@Service("simpleMailService")
@ConditionalOnMissingBean(name = "mailService")
@Validated
public class SimpleMailServiceImpl implements MailService {
    @Autowired
    private SmtpConnectionPool smtpConnectionPool;
    @Autowired
    private Validator validator;

    @Async
    @Override
    public void sendMail(String target, String code) throws Exception {
        log.info("send code{} ->:{} ", code, target);
        ClosableSmtpConnection transport = smtpConnectionPool.borrowObject();
        MimeMessage mimeMessage = new MimeMessage(transport.getSession());
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
        mimeMessage.setText("code:" + code);
        mimeMessage.setSubject("你的验证码是");
        mimeMessage.setFrom("javaxuniji@gmail.com");
        mimeMessage.setSentDate(new Date());
        try {
            transport.sendMessage(mimeMessage);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void sendWarnMail(EmailWarnTo emailWarnTo) throws Exception {
        log.info("send warn mail to {} ", emailWarnTo.getTarget());
        Set<ConstraintViolation<EmailWarnTo>> violations = validator.validate(emailWarnTo);
        if (violations.size() > 0) {
            throw new Exception(violations.iterator().next().getMessage());
        } else {
            ClosableSmtpConnection transport = smtpConnectionPool.borrowObject();
            MimeMessage mimeMessage = new MimeMessage(transport.getSession());
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailWarnTo.getTarget()));
            mimeMessage.setText(emailWarnTo.getContent());
            mimeMessage.setSubject(emailWarnTo.getSubject());
            transport.sendMessage(mimeMessage);
        }

    }
}
