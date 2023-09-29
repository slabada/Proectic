package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

@Getter
public class TaxBenefitDTO {
    private long id;
    private int percentage;
    private String name;
}
