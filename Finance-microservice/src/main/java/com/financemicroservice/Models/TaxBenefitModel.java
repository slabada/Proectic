package com.financemicroservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
