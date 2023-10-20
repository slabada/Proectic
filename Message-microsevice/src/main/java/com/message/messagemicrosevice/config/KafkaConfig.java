package com.message.messagemicrosevice.config;

import com.message.messagemicrosevice.DTOModels.SendKafkaDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // Конфигурация фабрики потребителей Kafka
    @Bean
    public ConsumerFactory<Long, SendKafkaDTO> ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfigs());
    }

    // Настройки для фабрики потребителей Kafka
    @Bean
    public Map<String, Object> ConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();

        // Указываем адрес сервера Kafka (брокер)
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Указываем идентификатор группы для этого потребителя
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sendKafka");

        // Указываем классы десериализации ключей и значений
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);

        // Указываем соответствие типов (сопоставление) для десериализации значений
        props.put(JsonDeserializer.TYPE_MAPPINGS, "sendKafkaDTO:com.message.messagemicrosevice.DTOModels.SendKafkaDTO");

        return props;
    }

    // Фабрика контейнеров для прослушивания Kafka-сообщений
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Long, SendKafkaDTO>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, SendKafkaDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ConsumerFactory());

        return factory;
    }
}

