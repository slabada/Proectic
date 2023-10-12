package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

// Льгота которая получается с PayRollCardDTO

@Getter
public class TaxBenefitDTO {
    private long id;
    private int percentage;
    private String name;
}
