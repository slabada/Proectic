package com.employeemanagement.employeemanagementmicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Модель должности для сотрудника

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1)
    private long id;
    @NotNull
    @Size(min = 3)
    private String name;
}
