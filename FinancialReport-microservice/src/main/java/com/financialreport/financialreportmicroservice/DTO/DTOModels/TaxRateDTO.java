package com.financialreport.financialreportmicroservice.DTO.DTOModels;

import lombok.Getter;

@Getter
public class TaxRateDTO {
    private long id;
    private int percentage;
    private String name;
}
