package com.financialreport.financialreportmicroservice.DTOModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Льгота которая получается с PayRollCardDTO

@Getter
@AllArgsConstructor
public class TaxBenefitDTO {
    private long id;
    private int percentage;
    private String name;
}
