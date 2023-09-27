package com.message.messagemicrosevice.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Conversion {
    public JsonNode toJson(String jsonEmployee) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonEmployee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
