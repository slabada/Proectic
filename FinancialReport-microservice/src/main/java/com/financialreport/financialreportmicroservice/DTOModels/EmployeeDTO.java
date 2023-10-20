package com.financialreport.financialreportmicroservice.DTOModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Поля которые запрашиваются у микросервиса Employee-Management-microservice
@Getter
@AllArgsConstructor
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String lastName;
}
