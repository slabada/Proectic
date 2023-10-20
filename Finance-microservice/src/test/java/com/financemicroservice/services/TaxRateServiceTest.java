package com.financemicroservice.services;

import com.financemicroservice.models.TaxRateModel;
import com.financemicroservice.repository.TaxRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaxRateServiceTest {
    private TaxRateService taxRateService;

    @Mock
    private TaxRateRepository taxRateRepository;

    TaxRateModel taxRateModel;

    TaxRateModel newTaxRateModel;

    @BeforeEach
    void setUp() {

        taxRateRepository = Mockito.mock(TaxRateRepository.class);

        taxRateService = new TaxRateService(taxRateRepository);

        taxRateModel = new TaxRateModel(
                1,
                13,
                "НДФЛ"
        );

        newTaxRateModel = new TaxRateModel(
                1,
                15,
                "НДФЛ"
        );
    }

    @Test
    void create() {
        // Мокируем поведение репозитория
        when(taxRateRepository.findByName("НДФЛ")).thenReturn(Optional.empty());

        // Вызываем метод create
        Optional<TaxRateModel> createdTaxRate = taxRateService.create(taxRateModel);

        assertTrue(createdTaxRate.isPresent());

        // Проверяем, что созданная налоговая ставка соответствует ожиданиям
        assertEquals(taxRateModel, createdTaxRate.get());
    }

    @Test
    void get() {
        long validId = 1;

        // Мокируем поведение репозитория, чтобы он возвращал существующую налоговую ставку
        when(taxRateRepository.findById(validId)).thenReturn(Optional.of(taxRateModel));

        // Вызываем метод get
        Optional<TaxRateModel> foundTaxRate = taxRateService.get(validId);

        // Проверяем, что найденная налоговая ставка соответствует ожиданиям
        assertTrue(foundTaxRate.isPresent()); // Проверка, что Optional содержит значение
        assertEquals(taxRateModel, foundTaxRate.get()); // Проверка на равенство
    }

    @Test
    void put() {
        long validId = 1;

        // Мокируем поведение репозитория, чтобы он возвращал существующую налоговую ставку и не находил по имени
        when(taxRateRepository.findById(validId)).thenReturn(Optional.of(taxRateModel));
        when(taxRateRepository.findByName("НДФЛ")).thenReturn(Optional.empty());

        // Вызываем метод put
        Optional<TaxRateModel> updatedRate = taxRateService.put(validId, newTaxRateModel);

        // Проверяем, что обновленная налоговая ставка соответствует ожиданиям
        assertTrue(updatedRate.isPresent()); // Проверка, что Optional содержит значение
        assertEquals(newTaxRateModel, updatedRate.get()); // Проверка на равенство
    }

    @Test
    void delete() {
        // Подготовка данных для теста.
        long validId = 1;

        // Mock вызов taxRateRepository.findById(validId) для возврата существующей налоговой ставки.
        when(taxRateRepository.findById(validId))
                .thenReturn(Optional.ofNullable(taxRateModel));

        // Вызываем метод delete для taxRateService.
        taxRateService.delete(validId);

        // Проверяем, что вызов taxRateRepository.deleteById был выполнен.
        Mockito.verify(taxRateRepository).deleteById(validId);
    }
}