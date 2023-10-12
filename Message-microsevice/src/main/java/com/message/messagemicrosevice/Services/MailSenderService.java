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

    // Значения, загруженные из конфигурации Spring
    @Value("${spring.mail.username}")
    private String username;

    @Value("${subject.mail}")
    private String subject;

    public MailSenderService(JavaMailSender sender, Format format, Conversion conversion) {
        this.sender = sender;
        this.format = format;
        this.conversion = conversion;
    }

    // Аннотация @KafkaListener указывает на то, что метод KafkaListen будет слушать сообщения из указанной темы Kafka
    @KafkaListener(id = "jsonEmployee", topics = "SendBuffer")
    private void KafkaListen(String jsonEmployee) {
        // При получении сообщения из Kafka, вызывается метод Send
        Send(jsonEmployee);
    }

    // Метод Send для отправки электронного письма
    public void Send(String jsonEmployee) {
        // Создание объекта SimpleMailMessage для формирования письма
        SimpleMailMessage ms = new SimpleMailMessage();

        // Установка отправителя, получателя, темы и текста письма
        ms.setFrom(username);
        ms.setTo(conversion.toJson(jsonEmployee).get("email").toString());
        ms.setSubject(subject);
        ms.setText(format.formattedText(jsonEmployee));

        // Использование JavaMailSender для отправки письма
        sender.send(ms);
    }
}

