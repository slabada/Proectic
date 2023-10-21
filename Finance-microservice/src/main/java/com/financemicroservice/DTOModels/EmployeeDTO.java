package com.financemicroservice.DTOModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// поля которые запрашиваются у микросервиса Employee-Management-microservice

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String email;
}
