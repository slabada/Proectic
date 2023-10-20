package com.financemicroservice.services;

import com.financemicroservice.models.PayRollCardModel;
import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.models.TaxRateModel;
import com.financemicroservice.repository.PayRollCardRepository;
import com.financemicroservice.serviceClient.EmployeeServiceClient;
import com.financemicroservice.util.TaxCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PayRollCardServiceTest {

    private PayRollCardService payRollCardService;

    @Mock
    private PayRollCardRepository payRollCardRepository;

    @Mock
    private TaxBenefitService taxBenefitService;

    @Mock
    private TaxRateService taxRateService;

    @Mock
    private KafkaService kafkaService;

    @Mock
    private TaxCalculation taxCalculation;

    @Mock
    private EmployeeServiceClient employeeServiceClient;

    private PayRollCardModel payRollCardModel;

    private PayRollCardModel newPayRollCardModel;

    private TaxBenefitModel taxBenefitModel;

    private TaxRateModel taxRateModel;

    @BeforeEach
    void setUp() {
        payRollCardRepository = Mockito.mock(PayRollCardRepository.class);
        taxBenefitService = Mockito.mock(TaxBenefitService.class);
        taxRateService = Mockito.mock(TaxRateService.class);
        kafkaService = Mockito.mock(KafkaService.class);
        taxCalculation = Mockito.mock(TaxCalculation.class);
        employeeServiceClient = Mockito.mock(EmployeeServiceClient.class);

        payRollCardService = new PayRollCardService(
                payRollCardRepository,
                taxBenefitService,
                taxRateService,
                kafkaService,
                taxCalculation,
                employeeServiceClient
        );

        taxBenefitModel = new TaxBenefitModel(
                1,
                13,
                "НДФЛ"
        );

        taxRateModel = new TaxRateModel(
                1,
                13,
                "НДФЛ"
        );

        payRollCardModel = new PayRollCardModel(
                1,
                1,
                new BigDecimal(6000),
                LocalDate.now().plusDays(1),
                null,
                Set.of(taxRateModel),
                new BigDecimal(6000).setScale(2, RoundingMode.HALF_DOWN)
        );

        newPayRollCardModel = new PayRollCardModel(
                1,
                1,
                new BigDecimal(5000),
                LocalDate.now().plusDays(1),
                null,
                Set.of(taxRateModel),
                new BigDecimal(5000).setScale(2, RoundingMode.HALF_DOWN)
        );
    }

    @Test
    void create() {

        Mockito.when(taxBenefitService.check(payRollCardModel))
                .thenReturn(Collections.singleton(taxBenefitModel));

        Mockito.when(taxRateService.check(payRollCardModel))
                .thenReturn(Collections.singleton(taxRateModel));

        Mockito.when(taxCalculation.totalRatePercentage(Collections.singleton(taxRateModel)))
                .thenReturn(13);

        Mockito.when(taxCalculation.totalBenefitPercentage(Collections.singleton(taxBenefitModel)))
                .thenReturn(2);

        BigDecimal calculatedTotalAmount = new BigDecimal(10000);

        Mockito.when(taxCalculation.calculation(
                        payRollCardModel.getSalary(),
                        13,
                        2
                )
        ).thenReturn(calculatedTotalAmount);

        payRollCardModel.setTotalAmount(calculatedTotalAmount.setScale(2, RoundingMode.HALF_DOWN));

        // Вызов метода, который мы тестируем
        Optional<PayRollCardModel> result = payRollCardService.create(payRollCardModel);

        // Проверка
        assertEquals(Optional.of(payRollCardModel), result);
    }


    @Test
    void get() {
        when(payRollCardRepository.findById(1L)).thenReturn(Optional.of(payRollCardModel));

        Optional<PayRollCardModel> retrievedPayRollCard = payRollCardService.get(1);

        assertTrue(retrievedPayRollCard.isPresent());

        assertEquals(payRollCardModel, retrievedPayRollCard.get());
    }

    @Test
    void getEmployeeAll() {
        when(payRollCardRepository.findAllByEmployeeId(1)).thenReturn(List.of(payRollCardModel));

        List<PayRollCardModel> payRollCards = payRollCardService.getEmployeeAll(1);

        assertFalse(payRollCards.isEmpty());

        assertEquals(payRollCardModel, payRollCards.get(0));
    }

    @Test
    void put() {

        long id = 1L;

        // Замокаем findById для возвращения payRollCardModel при запросе по id
        when(payRollCardRepository.findById(id))
                .thenReturn(Optional.of(payRollCardModel));

        // Замокаем остальные вызовы, чтобы не выбрасывать исключения

        Mockito.when(taxBenefitService.check(newPayRollCardModel))
                .thenReturn(Collections.singleton(taxBenefitModel));

        Mockito.when(taxRateService.check(newPayRollCardModel))
                .thenReturn(Collections.singleton(taxRateModel));

        Mockito.when(taxCalculation.totalRatePercentage(Collections.singleton(taxRateModel)))
                .thenReturn(13);

        Mockito.when(taxCalculation.totalBenefitPercentage(Collections.singleton(taxBenefitModel)))
                .thenReturn(2);

        BigDecimal calculatedTotalAmount = new BigDecimal(10000);

        Mockito.when(taxCalculation.calculation(
                        newPayRollCardModel.getSalary(),
                        13,
                        2
                )
        ).thenReturn(calculatedTotalAmount);

        newPayRollCardModel.setTotalAmount(calculatedTotalAmount.setScale(2, RoundingMode.HALF_DOWN));

        // Вызов метода, который мы тестируем
        PayRollCardModel updatedPayRollCard = payRollCardService.put(id, newPayRollCardModel);

        // Проверка
        assertEquals(newPayRollCardModel, updatedPayRollCard);
    }

    @Test
    void delete() {
        // Подготовка данных для теста.
        long validId = 1;

        // Mock вызов employeeRepository.findById(validId) для возврата существующего сотрудника.
        Mockito.when(payRollCardRepository.findById(validId))
                .thenReturn(Optional.ofNullable(payRollCardModel));

        // Вызываем метод delete для employeeService.
        payRollCardService.delete(validId);

        // Проверяем, что вызов employeeRepository.deleteById был выполнен.
        Mockito.verify(payRollCardRepository).deleteById(validId);
    }
}