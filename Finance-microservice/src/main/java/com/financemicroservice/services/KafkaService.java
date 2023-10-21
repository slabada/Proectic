package com.financemicroservice.services;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.financemicroservice.DTOModels.EmployeeDTO;
import com.financemicroservice.DTOModels.SendKafkaDTO;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class KafkaService {

    protected final KafkaTemplate<Long, SendKafkaDTO> template;

    public KafkaService(KafkaTemplate<Long, SendKafkaDTO> template) {
        this.template = template;
    }

    // Метод для отправки сообщения в Kafka с данными о сотруднике и другой информации.
    public void sendBufferKafka(EmployeeDTO employee, LocalDate data, BigDecimal totalAmount){

        // Формируем что отправлять в кафку
        SendKafkaDTO send = new SendKafkaDTO(
                employee.getEmail(),
                employee.getFirstName(),
                data,
                totalAmount
        );

        // Отправка JSON-строки в Kafka топик "SendBuffer".
        template.send("SendBuffer", 1L, send);
    }
}

