package com.message.messagemicrosevice.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Conversion {
    // Метод toJson для преобразования JSON-строки в объект JsonNode
    public JsonNode toJson(String jsonEmployee) {
        // Создание объекта ObjectMapper из библиотеки Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Попытка разбора JSON-строки и преобразования её в объект JsonNode
            return objectMapper.readTree(jsonEmployee);
        } catch (JsonProcessingException e) {
            // В случае ошибки при разборе, выбрасывается исключение RuntimeException
            throw new RuntimeException(e);
        }
    }
}

