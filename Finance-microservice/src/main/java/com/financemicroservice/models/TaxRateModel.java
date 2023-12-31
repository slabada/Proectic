package com.financemicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Реализация абстрактного класса для налога

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Rate")
public class TaxRateModel extends BaseModel{
    public TaxRateModel(long id, int percentage, String name) {
        super(id, percentage, name);
    }
}
