package com.message.messagemicrosevice.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfiguration {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender sender(){
        JavaMailSenderImpl s = new JavaMailSenderImpl();

        s.setHost(host);s.setUsername(username);
        s.setPassword(password);
        s.setPort(port);
        s.setProtocol(protocol);

        Properties p = s.getJavaMailProperties();

        p.setProperty("mail.transport.protocol", protocol);
        p.setProperty("mail.debug", debug);

        return s;
    }
}
