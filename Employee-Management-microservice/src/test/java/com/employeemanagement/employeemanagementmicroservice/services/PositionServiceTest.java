package com.employeemanagement.employeemanagementmicroservice.services;

import com.employeemanagement.employeemanagementmicroservice.customRepository.CustomPositionRepository;
import com.employeemanagement.employeemanagementmicroservice.models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.repository.PositionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionServiceTest {

    // Объявление зависимостей и создание экземпляра PositionService
    private PositionService positionService;
    @Mock
    private PositionRepository positionRepository;
    @Mock
    private CustomPositionRepository customPositionRepository;

    PositionModel newPosition;

    PositionModel Position;

    @BeforeEach
    void setUp() {
        // Создание заглушек (mock) для репозиториев и инициализация positionService
        positionRepository = Mockito.mock(PositionRepository.class);
        customPositionRepository = Mockito.mock(CustomPositionRepository.class);
        positionService = new PositionService(
                positionRepository,
                customPositionRepository
        );

        // Инициализация объектов Position и newPosition
        Position = new PositionModel(
                1,
                "Programmers"
        );
        newPosition = new PositionModel(
                1,
                "Programmer"
        );
    }

    @AfterEach
    void tearDown() {
        // Пустой метод, не требуется в данном случае
    }

    @Test
    void create() {
        // Мокирование поведения positionRepository.existsByName
        Mockito.when(positionRepository.existsByName(Position.getName())).thenReturn(false);

        // Вызов метода positionService.create и проверка результата
        PositionModel createdPosition = positionService.create(Position);
        assertEquals(Position, createdPosition); // Проверка на равенство
        Mockito.verify(positionRepository).save(Position); // Проверка вызова метода save у positionRepository
    }

    @Test
    void get() {
        // Инициализация validId
        long validId = 1;

        // Мокирование поведения customPositionRepository.findById
        Mockito.when(customPositionRepository.findById(validId))
                .thenReturn(Optional.ofNullable(Position));

        // Вызов метода positionService.get и проверка результата
        Optional<PositionModel> getPosition = positionService.get(validId);
        assertTrue(getPosition.isPresent()); // Проверка, что Optional содержит значение
        assertEquals(Position, getPosition.get()); // Проверка на равенство
    }

    @Test
    void put() {
        // Инициализация validId
        long validId = 1;

        // Мокирование поведения positionRepository.findById
        Mockito.when(positionRepository.findById(validId))
                .thenReturn(Optional.ofNullable(Position));

        // Вызов метода positionService.put и проверка результата
        PositionModel putPosition = positionService.put(validId, newPosition);
        Mockito.verify(positionRepository).save(newPosition); // Проверка вызова метода save у positionRepository
        assertEquals(newPosition, putPosition); // Проверка на равенство
    }

    @Test
    void delete() {
        // Инициализация validId
        long validId = 1;

        // Мокирование поведения positionRepository.findById
        Mockito.when(positionRepository.findById(validId))
                .thenReturn(Optional.ofNullable(Position));

        // Вызов метода positionService.delete и проверка вызова метода deleteById у positionRepository
        positionService.delete(validId);
        Mockito.verify(positionRepository).deleteById(validId);
    }
}
