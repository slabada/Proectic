package com.financialreport.financialreportmicroservice.service;

import com.financialreport.financialreportmicroservice.DTOModels.EmployeeDTO;
import com.financialreport.financialreportmicroservice.DTOModels.PayRollCardDTO;
import com.financialreport.financialreportmicroservice.DTOModels.TaxBenefitDTO;
import com.financialreport.financialreportmicroservice.DTOModels.TaxRateDTO;
import com.financialreport.financialreportmicroservice.seviceClient.EmployeeServiceClient;
import com.financialreport.financialreportmicroservice.seviceClient.FinanceEmployeeServiceClient;
import org.handler.CustomExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportServiceTest {

    private ReportService reportService;
    @Mock
    private EmployeeServiceClient employeeServiceClient;
    @Mock
    private FinanceEmployeeServiceClient financeEmployeeServiceClient;

    @BeforeEach
    void setUp() {

        employeeServiceClient = Mockito.mock(EmployeeServiceClient.class);

        financeEmployeeServiceClient = Mockito.mock(FinanceEmployeeServiceClient.class);

        reportService = new ReportService(
                employeeServiceClient,
                financeEmployeeServiceClient
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmployee() {
        // Подготовка данных для теста
        long validId = 1;

        // Здесь мы настраиваем поведение mock объекта, чтобы он возвращал какие-то фиктивные данные
        // когда вызывается метод getEmployee с допустимым идентификатором
        EmployeeDTO expectedEmployee = new EmployeeDTO(
                validId,
                "User1",
                "User2"
        );

        Mockito.when(employeeServiceClient.getEmployee(validId)).thenReturn(expectedEmployee);

        EmployeeDTO result = reportService.getEmployee(validId);

        // Проверяем, что результат метода соответствует ожидаемым данным
        assertEquals(expectedEmployee, result);
    }

    @Test
    void getPayRollCard() {

        // Подготовка данных для теста
        long validId = 1;

        List<PayRollCardDTO> expectedPayRollCardDTO = new ArrayList<>();

        expectedPayRollCardDTO.add(
                new PayRollCardDTO(
                        1,
                        2,
                        new BigDecimal("50000.00"),
                        LocalDate.of(2023, 10, 18),
                        Collections.singleton(new TaxBenefitDTO(
                                1,
                                15,
                                "test"
                        )),
                        Collections.singleton(new TaxRateDTO(
                                1,
                                15,
                                "test"
                        )),
                        new BigDecimal("40.000")
                )
        );

        Mockito.when(financeEmployeeServiceClient.getFinanceEmployee(validId))
                .thenReturn(expectedPayRollCardDTO);

        List<PayRollCardDTO> result = reportService.getPayRollCard(validId);

        assertEquals(expectedPayRollCardDTO, result);
    }
}