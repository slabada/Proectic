package com.message.messagemicrosevice.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class Format {
    protected final Conversion conversion;

    // Значение, загруженное из конфигурации Spring
    @Value("${text.mail}")
    private String text;

    public Format(Conversion conversion) {
        this.conversion = conversion;
    }

    // Метод formattedText для форматирования текста письма
    public String formattedText(String jsonEmployee) {
        // Извлечение значений из JSON-строки с использованием сервиса Conversion
        String name = conversion.toJson(jsonEmployee).get("firstName").toString();
        String data = conversion.toJson(jsonEmployee).get("data").toString();
        String totalAmount = conversion.toJson(jsonEmployee).get("totalAmount").toString();

        // Форматирование текста с использованием значений из JSON и текста из конфигурации
        return MessageFormat.format(text, name, data, totalAmount).replace("\"", "");
    }
}

