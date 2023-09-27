package com.financemicroservice.Util;

import com.financemicroservice.Models.BaseModel;
import com.financemicroservice.Models.TaxBenefitModel;
import com.financemicroservice.Models.TaxRateModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class TaxCalculation {

    public BigDecimal Calculation(BigDecimal salary, int ratePercentage, int benefitPercentage){
        BigDecimal rate = new BigDecimal(ratePercentage).divide(new BigDecimal(100));
        BigDecimal benefit = new BigDecimal(benefitPercentage).divide(new BigDecimal(100));
        return salary.subtract(salary.multiply(rate.subtract(benefit)));
    }

    public BigDecimal Calculation(BigDecimal salary, int ratePercentage){
        BigDecimal rate = new BigDecimal(ratePercentage).divide(new BigDecimal(100));
        return salary.subtract(salary.multiply(rate));
    }

    public int totalBenefitPercentage(Set<TaxBenefitModel> b){
        return b.stream().mapToInt(BaseModel::getPercentage).sum();
    }

    public int totalRatePercentage(Set<TaxRateModel> r){
        return r.stream().mapToInt(BaseModel::getPercentage).sum();
    }
}
