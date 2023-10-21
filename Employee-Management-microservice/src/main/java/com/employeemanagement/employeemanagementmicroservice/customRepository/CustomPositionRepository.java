package com.employeemanagement.employeemanagementmicroservice.customRepository;

import com.employeemanagement.employeemanagementmicroservice.models.PositionModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Реализация EntityManager

@Repository
public class CustomPositionRepository {

    private final EntityManager entityManager;

    public CustomPositionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Метод для поиска должности по идентификатору.
    public Optional<PositionModel> findById(Long id){

        // Создание типизированного запроса для поиска должности по ID.
        TypedQuery<PositionModel> q = entityManager.createQuery(
                """
                   FROM PositionModel e WHERE e.id = :id
                   """,
                PositionModel.class
        ).setParameter("id", id);

        // Получение результата запроса в виде списка.
        List<PositionModel> p = q.getResultList();

        // Проверка, была ли найдена должность по-заданному ID, и возврат Optional.
        if(p.isEmpty()){
            return Optional.empty();
        }
        else {
            return Optional.of(p.get(0));
        }
    }
}
