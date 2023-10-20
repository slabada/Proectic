package com.employeemanagement.employeemanagementmicroservice.repository;

import com.employeemanagement.employeemanagementmicroservice.models.EmployeeModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Репозиторий :)
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {



    // Пользовательский метод для поиска сотрудников на основе фильтра
    @Query("""
    SELECT x from EmployeeModel x
    WHERE (:#{#filter.lastName} IS NULL OR x.lastName = :#{#filter.lastName})
    and (:#{#filter.firstName} IS NULL OR x.firstName = :#{#filter.firstName})
    and (:#{#filter.address} IS NULL OR x.address = :#{#filter.address})
    and (:#{#filter.email} IS NULL OR x.email = :#{#filter.email})
    and (:#{#filter.position} IS NULL OR x.position = :#{#filter.position})
    """)
    List<EmployeeModel> Search(@Param("filter") EmployeeModel employee, PageRequest page);
}