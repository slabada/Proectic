package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

// Поля которые запрашиваются у микросервиса Employee-Management-microservice

@Getter
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String lastName;
}
