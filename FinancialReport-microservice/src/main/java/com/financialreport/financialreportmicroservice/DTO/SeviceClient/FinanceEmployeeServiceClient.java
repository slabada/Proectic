package com.financialreport.financialreportmicroservice.DTO.SeviceClient;

import com.financialreport.financialreportmicroservice.DTO.DTOModels.PayRollCardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FinanceEmployeeServiceClient", url = "http://localhost:8082")
public interface FinanceEmployeeServiceClient {
    @GetMapping("/salary/employee/{id}")
    List<PayRollCardDTO> getFinanceEmployee(@PathVariable Long id);
}
