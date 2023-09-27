package com.message.messagemicrosevice.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class Format {

    protected final Conversion conversion;

    @Value("${text.mail}")
    private String text;

    public Format(Conversion conversion) {
        this.conversion = conversion;
    }

    public String formattedText(String jsonEmployee){
        String name = conversion.toJson(jsonEmployee).get("firstName").toString();
        String data = conversion.toJson(jsonEmployee).get("data").toString();
        String totalAmount = conversion.toJson(jsonEmployee).get("totalAmount").toString();
        return MessageFormat.format(text, name, data, totalAmount).replace("\"", "");
    }
}
