package cloud.stackexplode.gulimall.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
class MailApplicationTests {
@Autowired private JavaMailSender javaMailSender;
	@Test
	void contextLoads() {
		// 构建一个邮件对象
		SimpleMailMessage message = new SimpleMailMessage();
		// 设置邮件主题
		message.setSubject("你的验证码是");
		// 设置邮件发送者，这个跟application.yml中设置的要一致
		message.setFrom("javaxuniji@gmail.com");
		// 设置邮件接收者，可以有多个接收者，多个接受者参数需要数组形式
		// message.setTo("1*****9@qq.com");
		message.setTo("2653084650@qq.com");
		// 设置邮件发送日期
		message.setSentDate(new Date());
		// 设置邮件的正文
		message.setText("code: 123456");
		// 发送邮件
		javaMailSender.send(message);
	}
	@Test
void testHtmlMail() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
		mimeMessageHelper.setTo("2653084650@qq.com");
		mimeMessageHelper.setFrom("javaxuniji@gmail");
		mimeMessageHelper.setSubject("你的验证码");
		mimeMessage.setFrom("javaxuniji@gmail");
		Context context = new Context();
		context.setVariable("code", "123456");
	}
}
