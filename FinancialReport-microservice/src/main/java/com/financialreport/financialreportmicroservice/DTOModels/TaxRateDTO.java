package com.financialreport.financialreportmicroservice.DTOModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Налог которая получается с PayRollCardDTO

@Getter
@AllArgsConstructor
public class TaxRateDTO {
    private long id;
    private int percentage;
    private String name;
}
