package com.employeemanagement.employeemanagementmicroservice.customRepository;

import com.employeemanagement.employeemanagementmicroservice.models.EmployeeModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Реализация Criteria API

@Repository
public class CustomEmployeeRepository {

    private final EntityManager entityManager;

    public CustomEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Метод для поиска сотрудника по идентификатору.
    public Optional<EmployeeModel> findById(Long id){
        try {
            // Получение CriteriaBuilder, который используется для создания запроса.
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            // Создание CriteriaQuery с указанием типа результата (EmployeeModel).
            CriteriaQuery<EmployeeModel> q = cb.createQuery(EmployeeModel.class);

            // Создание корневого элемента для запроса, связанного с сущностью EmployeeModel.
            Root<EmployeeModel> r = q.from(EmployeeModel.class);

            // Выборка всех полей сущности EmployeeModel, где ID совпадает с заданным значением.
            q.select(r).where(cb.equal(r.get("id"), id));

            // Выполнение запроса и получение результата, ожидая одного результата.
            EmployeeModel employee = entityManager.createQuery(q).getSingleResult();

            return Optional.of(employee);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
