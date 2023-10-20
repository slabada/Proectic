package com.employeemanagement.employeemanagementmicroservice.services;

import com.employeemanagement.employeemanagementmicroservice.customRepository.CustomEmployeeRepository;
import com.employeemanagement.employeemanagementmicroservice.models.EmployeeModel;
import com.employeemanagement.employeemanagementmicroservice.models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private PositionService positionService;
    @Mock
    private CustomEmployeeRepository customEmployeeRepository;

    private EmployeeModel employeeModel;

    private PositionModel positionModel;

    private final List<EmployeeModel> searchResults = new ArrayList<>();

    @BeforeEach
    void setUp() {

        employeeRepository = Mockito.mock(EmployeeRepository.class);

        positionService = Mockito.mock(PositionService.class);

        customEmployeeRepository = Mockito.mock(CustomEmployeeRepository.class);

        employeeService = new EmployeeService(
                employeeRepository,
                positionService,
                customEmployeeRepository
        );

        positionModel = new PositionModel(
                1,
                "programmer"
        );

        employeeModel = new EmployeeModel(
                1,
                "Женя",
                "Пригожин",
                "evgesha@gmail.com",
                "РОССИЯ",
                positionModel
        );

        searchResults.add(
                new EmployeeModel(
                        1,
                        "Женя",
                        "Пригожин",
                        "evgesha@gmail.com",
                        "РОССИЯ",
                        positionModel
                )
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {

        long validId = 1;

        // Mock вызов positionService.get(validId) для возврата positionModel.
        Mockito.when(positionService.get(validId))
                .thenReturn(Optional.ofNullable(positionModel));

        // Вызываем метод create для employeeService.
        EmployeeModel createdEmployee = employeeService.create(employeeModel);

        // Проверяем, что вызов positionService.get был выполнен.
        Mockito.verify(positionService).get(validId);

        // Проверяем, что созданный сотрудник не является null.
        assertNotNull(createdEmployee);

        // Проверяем, что созданный сотрудник соответствует ожиданиям.
        assertEquals(employeeModel, createdEmployee);

        // Проверяем, что сотрудник имеет правильную должность.
        assertEquals(positionModel, createdEmployee.getPosition());

        // Проверяем, что метод save был вызван с правильным сотрудником.
        Mockito.verify(employeeRepository).save(employeeModel);
    }

    @Test
    void get() {

        long validId = 1;

        // Mock вызов customEmployeeRepository.findById(validId) для возврата employeeModel.
        Mockito.when(customEmployeeRepository.findById(validId))
                .thenReturn(Optional.ofNullable(employeeModel));

        // Вызываем метод get для employeeService.
        Optional<EmployeeModel> result = employeeService.get(validId);

        // Проверяем, что вызов customEmployeeRepository.findById был выполнен.
        Mockito.verify(customEmployeeRepository).findById(validId);

        // Проверяем, что результат не пустой.
        assertTrue(result.isPresent());

        // Проверяем, что результат соответствует ожиданиям.
        assertEquals(employeeModel, result.get());
    }

    @Test
    void put() {
        // Подготовка данных для теста.
        long validId = 1;

        // Mock вызов employeeRepository.findById(validId) для возврата существующего сотрудника.
        Mockito.when(employeeRepository.findById(validId))
                .thenReturn(Optional.ofNullable(employeeModel));

        // Mock вызов positionService.get(e.getPosition().getId()) для возврата должности.
        Mockito.when(positionService.get(positionModel.getId())).thenReturn(Optional.ofNullable(positionModel));

        // Вызываем метод put для employeeService.
        EmployeeModel updatedEmployee = employeeService.put(validId, employeeModel);

        // Проверяем, что вызов employeeRepository.save был выполнен.
        Mockito.verify(employeeRepository).save(employeeModel);

        // Проверяем, что сотрудник обновлен и содержит правильную должность.
        assertEquals(validId, updatedEmployee.getId());
        assertEquals(positionModel, updatedEmployee.getPosition());
    }

    @Test
    void delete() {
        // Подготовка данных для теста.
        long validId = 1;

        // Mock вызов employeeRepository.findById(validId) для возврата существующего сотрудника.
        Mockito.when(employeeRepository.findById(validId)).thenReturn(Optional.ofNullable(employeeModel));

        // Вызываем метод delete для employeeService.
        employeeService.delete(validId);

        // Проверяем, что вызов employeeRepository.deleteById был выполнен.
        Mockito.verify(employeeRepository).deleteById(validId);
    }

    @Test
    void search() {
        // Подготовка данных для теста.
        int from = 0;
        int size = 10;

        // Mock вызов employeeRepository.Search(searchParameters, page) для возврата searchResults.
        Mockito.when(employeeRepository.Search(employeeModel, PageRequest.of(from, size)))
                .thenReturn(searchResults);

        // Вызываем метод search для employeeService.
        List<EmployeeModel> result = employeeService.search(employeeModel, from, size);

        // Проверяем, что вызов employeeRepository.Search был выполнен с правильными параметрами.
        Mockito.verify(employeeRepository).Search(employeeModel, PageRequest.of(from, size));

        // Проверяем, что результат соответствует ожиданиям.
        assertEquals(searchResults, result);
    }
}