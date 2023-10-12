package com.financemicroservice.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

// Модель записи о выплате

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PayRollCard")
public class PayRollCardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long employeeId;
    private BigDecimal salary;
    private LocalDate payday;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "PayRollCard"),
            inverseJoinColumns = @JoinColumn(name = "Benefit_Id"),
            name = "PayRollCard_Benefit"
    )
    private Set<TaxBenefitModel> taxBenefit;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "PayRollCard"),
            inverseJoinColumns = @JoinColumn(name = "Rate_Id"),
            name = "PayRollCard_Rate"
    )
    private Set<TaxRateModel> taxRate;
    private BigDecimal totalAmount;
}
