package com.message.messagemicrosevice.services;

import com.message.messagemicrosevice.DTOModels.SendKafkaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class MailSenderService {

    protected final JavaMailSender sender;

    // Значения, загруженные из конфигурации Spring
    @Value("${spring.mail.username}")
    private String username;

    @Value("${subject.mail}")
    private String subject;

    // Значение, загруженное из конфигурации Spring
    @Value("${text.mail}")
    private String text;

    public MailSenderService(JavaMailSender sender) {
        this.sender = sender;
    }

    // Аннотация @KafkaListener указывает на то, что метод KafkaListen будет слушать сообщения из указанной темы Kafka
    @KafkaListener(id = "sendKafka", topics = "SendBuffer")
    private void kafkaListen(SendKafkaDTO sendKafkaDTO) {
        // При получении сообщения из Kafka, вызывается метод Send
        System.out.println(sendKafkaDTO.getFirstName());
    }

    // Метод Send для отправки электронного письма
    public void send(SendKafkaDTO sendKafkaDTO) {

        // Создание объекта SimpleMailMessage для формирования письма
        SimpleMailMessage ms = new SimpleMailMessage();

        //Форматируем текст для письма
        String massage = MessageFormat.format(
                text,
                sendKafkaDTO.getFirstName(),
                sendKafkaDTO.getData(),
                sendKafkaDTO.getTotalAmount()
                )
                .replace("\"", "");

        // Установка отправителя, получателя, темы и текста письма
        ms.setFrom(username);
        ms.setTo(sendKafkaDTO.getEmail());
        ms.setSubject(subject);
        ms.setText(massage);

        // Использование JavaMailSender для отправки письма
        sender.send(ms);
    }
}

