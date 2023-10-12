package com.financemicroservice.Configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    // Создание бина для определения темы Kafka.
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("SendBuffer") // Указание имени темы.
                .build(); // Создание и возвращение новой темы Kafka.
    }
}
