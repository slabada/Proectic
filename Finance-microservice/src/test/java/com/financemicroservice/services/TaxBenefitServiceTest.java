package com.financemicroservice.services;

import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.repository.TaxBenefitRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaxBenefitServiceTest {

    private TaxBenefitService taxBenefitService;

    @Mock
    private TaxBenefitRepository taxBenefitRepository;

    TaxBenefitModel taxBenefitModel;

    TaxBenefitModel newTaxBenefitModel;

    @BeforeEach
    void setUp() {

        taxBenefitRepository = mock(TaxBenefitRepository.class);

        taxBenefitService = new TaxBenefitService(taxBenefitRepository);

        taxBenefitModel = new TaxBenefitModel(
                1,
                13,
                "НДФЛ"
        );

        newTaxBenefitModel = new TaxBenefitModel(
                1,
                15,
                "НДФЛ"
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        // Мокируем поведение репозитория
        when(taxBenefitRepository.findByName("НДФЛ")).thenReturn(Optional.empty());

        // Вызываем метод create
        TaxBenefitModel createdTaxBenefit = taxBenefitService.create(taxBenefitModel);

        // Проверяем, что созданная налоговая льгота соответствует ожиданиям
        assertEquals(taxBenefitModel, createdTaxBenefit);
    }

    @Test
    void get() {
        long validId = 1;

        // Мокируем поведение репозитория, чтобы он возвращал существующую налоговую льготу
        when(taxBenefitRepository.findById(validId)).thenReturn(Optional.of(taxBenefitModel));

        // Вызываем метод get
        Optional<TaxBenefitModel> foundTaxBenefit = taxBenefitService.get(validId);

        // Проверяем, что найденная налоговая льгота соответствует ожиданиям
        assertTrue(foundTaxBenefit.isPresent()); // Проверка, что Optional содержит значение

        assertEquals(taxBenefitModel, foundTaxBenefit.get()); // Проверка на равенство
    }

    @Test
    void put() {
        long validId = 1;

        // Мокируем поведение репозитория, чтобы он возвращал существующую налоговую льготу и не находил по имени
        when(taxBenefitRepository.findById(validId)).thenReturn(Optional.of(taxBenefitModel));
        when(taxBenefitRepository.findByName("НДФЛ")).thenReturn(Optional.empty());

        // Вызываем метод put
        Optional<TaxBenefitModel> updatedBenefit = taxBenefitService.put(validId, newTaxBenefitModel);

        // Проверяем, что обновленная налоговая льгота соответствует ожиданиям
        assertTrue(updatedBenefit.isPresent()); // Проверка, что Optional содержит значение

        assertEquals(newTaxBenefitModel, updatedBenefit.get()); // Проверка на равенство
    }

    @Test
    void delete() {
        // Подготовка данных для теста.
        long validId = 1;

        // Mock вызов employeeRepository.findById(validId) для возврата существующего сотрудника.
        Mockito.when(taxBenefitRepository.findById(validId))
                .thenReturn(Optional.ofNullable(taxBenefitModel));

        // Вызываем метод delete для employeeService.
        taxBenefitService.delete(validId);

        // Проверяем, что вызов employeeRepository.deleteById был выполнен.
        Mockito.verify(taxBenefitRepository).deleteById(validId);
    }
}