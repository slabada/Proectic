package com.financialreport.financialreportmicroservice.DTO.SeviceClient;

import com.financialreport.financialreportmicroservice.DTO.DTOModels.EmployeeDTO;
import com.financialreport.financialreportmicroservice.DTO.DTOModels.PayRollCardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EmployeeServiceClient", url = "http://localhost:8081")
public interface EmployeeServiceClient {
    @GetMapping("/employee/{id}")
    EmployeeDTO getEmployee(@PathVariable Long id);
}
