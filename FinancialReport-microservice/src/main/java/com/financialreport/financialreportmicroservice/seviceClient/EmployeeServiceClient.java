package com.financialreport.financialreportmicroservice.seviceClient;

import com.financialreport.financialreportmicroservice.DTOModels.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FinancialReport-EmployeeServiceClient", url = "http://localhost:8081")
public interface EmployeeServiceClient {
    @GetMapping("/employee/{id}")
    EmployeeDTO getEmployee(@PathVariable Long id);
}
