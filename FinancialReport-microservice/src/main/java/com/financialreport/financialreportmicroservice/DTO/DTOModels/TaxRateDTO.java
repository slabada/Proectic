package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

// Налог которая получается с PayRollCardDTO

@Getter
public class TaxRateDTO {
    private long id;
    private int percentage;
    private String name;
}
