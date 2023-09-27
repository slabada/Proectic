package com.employeemanagement.employeemanagementmicroservice.Repository;

import com.employeemanagement.employeemanagementmicroservice.Models.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionModel, Long> {
    Boolean existsByName(String name);
}
