package com.message.messagemicrosevice.DTOModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendKafkaDTO {
    private String email;
    private String firstName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private BigDecimal totalAmount;
}
