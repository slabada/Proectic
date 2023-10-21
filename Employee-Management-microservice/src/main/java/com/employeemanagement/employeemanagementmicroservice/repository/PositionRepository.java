package com.employeemanagement.employeemanagementmicroservice.repository;

import com.employeemanagement.employeemanagementmicroservice.models.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Еще один репозиторий :)

@Repository
public interface PositionRepository extends JpaRepository<PositionModel, Long> {

    // Метод, который проверяет существование позиции по имени.
    Boolean existsByName(String name);
}
