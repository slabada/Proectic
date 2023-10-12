package com.financemicroservice.DTO.ServiceClient;


import com.financemicroservice.DTO.DTOModels.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Сам запрос API Employee-Management-microservice по определенном URL

@FeignClient(name = "EmployeeServiceClient", url = "http://localhost:8081")
public interface EmployeeServiceClient {
    @GetMapping("/employee/{id}")
    EmployeeDTO getPositionById(@PathVariable("id") Long id);
}
