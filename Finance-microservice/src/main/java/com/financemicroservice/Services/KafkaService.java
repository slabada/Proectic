package com.financemicroservice.Services;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.financemicroservice.DTO.DTOModels.EmployeeDTO;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class KafkaService {

    protected final KafkaTemplate<String, String> template;

    public KafkaService(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @SneakyThrows
    public void SendBufferKafka(EmployeeDTO employee, LocalDate data, BigDecimal totalAmount){

        ObjectNode json = JsonNodeFactory.instance.objectNode()
                .put("email", employee.getEmail())
                .put("firstName", employee.getFirstName())
                .put("data", data.toString())
                .put("totalAmount", totalAmount.toString());

        template.send("SendBuffer", json.toString());
    }

//    public void SendBufferKafka(EmployeeDTO employee, LocalDate data, BigDecimal totalAmount){
//
//        ObjectNode json = JsonNodeFactory.instance.objectNode()
//                .put("email", employee.get("email").asText())
//                .put("firstName", employee.get("firstName").asText())
//                .put("data", data.toString())
//                .put("totalAmount", totalAmount.toString());
//
//        template.send("SendBuffer", json.toString());
//    }
}
