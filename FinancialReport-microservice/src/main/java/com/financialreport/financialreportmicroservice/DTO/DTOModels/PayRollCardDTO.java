package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

// Поля, которые запрашиваются у микросервиса Finance-microservice

@Getter
public class PayRollCardDTO {
    private long id;
    private long employeeId;
    private BigDecimal salary;
    private LocalDate payday;
    private Set<TaxBenefitDTO> taxBenefit;
    private Set<TaxRateDTO> taxRate;
    private BigDecimal totalAmount;
}
