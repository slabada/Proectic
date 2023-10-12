package com.financemicroservice.DTO.DTOModels;

import lombok.Getter;

// поля которые запрашиваются у микросервиса Employee-Management-microservice

@Getter
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String email;
}
