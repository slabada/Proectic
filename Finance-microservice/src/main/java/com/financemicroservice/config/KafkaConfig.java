package com.financemicroservice.config;

import com.financemicroservice.DTOModels.SendKafkaDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // Создаем бин для определения темы Kafka.
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name("SendBuffer")
                .build();
    }

    // Конфигурация фабрики продюсера Kafka
    @Bean
    public ProducerFactory<Long, SendKafkaDTO> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    // Настройки для фабрики продюсера Kafka
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        // Указываем адрес сервера Kafka (брокер)
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Указываем классы сериализации ключей и значений
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Указываем соответствие типов (сопоставление) для сериализации значений
        props.put(JsonSerializer.TYPE_MAPPINGS, "sendKafkaDTO:com.financemicroservice.DTOModels.SendKafkaDTO");

        return props;
    }

    // Создаем шаблон Kafka для отправки сообщений
    @Bean
    public KafkaTemplate<Long, SendKafkaDTO> kafkaTemplate() {
        return new KafkaTemplate<Long, SendKafkaDTO>(producerFactory());
    }
}

