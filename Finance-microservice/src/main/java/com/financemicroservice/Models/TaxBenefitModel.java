package com.financemicroservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Реализация абстрактного класса для льготы

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TaxBenefit")
public class TaxBenefitModel extends BaseModel {
    public TaxBenefitModel(long id, int percentage, String name) {
        super(id, percentage, name);
    }
}
