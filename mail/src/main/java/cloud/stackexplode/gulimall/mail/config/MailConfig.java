package cloud.stackexplode.gulimall.mail.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.nlab.smtp.pool.SmtpConnectionPool;
import org.nlab.smtp.transport.factory.SmtpConnectionFactory;
import org.nlab.smtp.transport.factory.SmtpConnectionFactoryBuilder;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;


@Configuration
public class MailConfig {
    @Bean
    public SmtpConnectionFactory smtpConnectionFactory(MailProperties mailProperties) {
        Map<String, String> properties = mailProperties.getProperties();
        Properties properties1 = new Properties();
        properties1.putAll(properties);
        return SmtpConnectionFactoryBuilder
                .newSmtpBuilder().session(properties1)
                .host(mailProperties.getHost())
                .port(mailProperties.getPort())
                .username(mailProperties.getUsername())
                .password(mailProperties.getPassword())
                .protocol(mailProperties.getProtocol())
                .build();
    }

    @Bean
    public SmtpConnectionPool smtpConnectionPool(SmtpConnectionFactory smtpConnectionFactory) throws Exception {
        SmtpConnectionPool smtpConnectionPool = new SmtpConnectionPool(smtpConnectionFactory);
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setJmxEnabled(false);
        smtpConnectionPool.setConfig(genericObjectPoolConfig);
        smtpConnectionPool.preparePool();
        return smtpConnectionPool;
    }
}
