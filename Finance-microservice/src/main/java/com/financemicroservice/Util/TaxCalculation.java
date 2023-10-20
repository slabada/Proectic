package com.financemicroservice.util;

import com.financemicroservice.models.BaseModel;
import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.models.TaxRateModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class TaxCalculation {

    // Этот метод вычисляет налоговый расчет с учетом зарплаты, ставки в процентах и процента выгоды.
    public BigDecimal calculation(BigDecimal salary, int ratePercentage, int benefitPercentage){
        // Преобразуем процентные ставки и проценты выгоды в десятичные значения.
        BigDecimal rate = new BigDecimal(ratePercentage).divide(new BigDecimal(100));
        BigDecimal benefit = new BigDecimal(benefitPercentage).divide(new BigDecimal(100));
        // Вычисляем налоговый расчет и возвращаем результат.
        return salary.subtract(salary.multiply(rate.subtract(benefit)));
    }

    // Этот метод вычисляет налоговый расчет с учетом зарплаты и ставки в процентах.
    public BigDecimal calculation(BigDecimal salary, int ratePercentage){
        // Преобразуем процентную ставку в десятичное значение.
        BigDecimal rate = new BigDecimal(ratePercentage).divide(new BigDecimal(100));
        // Вычисляем налоговый расчет и возвращаем результат.
        return salary.subtract(salary.multiply(rate));
    }

    // Этот метод вычисляет общий процент выгоды на основе моделей налоговых выгод.
    public int totalBenefitPercentage(Set<TaxBenefitModel> b){
        // Суммируем проценты выгоды из моделей и возвращаем их сумму.
        return b.stream().mapToInt(BaseModel::getPercentage).sum();
    }

    // Этот метод вычисляет общий процент ставки налога на основе моделей налоговых ставок.
    public int totalRatePercentage(Set<TaxRateModel> r){
        // Суммируем проценты ставок налога из моделей и возвращаем их сумму.
        return r.stream().mapToInt(BaseModel::getPercentage).sum();
    }
}

