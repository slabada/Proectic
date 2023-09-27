package com.message.messagemicrosevice.Services;

import com.message.messagemicrosevice.Util.Conversion;
import com.message.messagemicrosevice.Util.Format;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    protected final JavaMailSender sender;
    protected final Format format;
    protected final Conversion conversion;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${subject.mail}")
    private String subject;

    public MailSenderService(JavaMailSender sender, Format format, Conversion conversion) {
        this.sender = sender;
        this.format = format;
        this.conversion = conversion;
    }

    @KafkaListener(id = "jsonEmployee", topics = "SendBuffer")
    private void KafkaListen(String jsonEmployee){
        Send(jsonEmployee);
    }

    public void Send(String je){
        SimpleMailMessage ms = new SimpleMailMessage();

        ms.setFrom(username);
        ms.setTo(conversion.toJson(je).get("email").toString());
        ms.setSubject(subject);
        ms.setText(format.formattedText(je));

        sender.send(ms);
    }
}
