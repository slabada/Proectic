package com.financemicroservice.DTO.DTOModels;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private PositionDTO position;
}
