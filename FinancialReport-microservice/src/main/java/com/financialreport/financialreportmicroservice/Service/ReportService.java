package com.financialreport.financialreportmicroservice.service;

import com.financialreport.financialreportmicroservice.DTOModels.EmployeeDTO;
import com.financialreport.financialreportmicroservice.DTOModels.PayRollCardDTO;
import com.financialreport.financialreportmicroservice.seviceClient.EmployeeServiceClient;
import com.financialreport.financialreportmicroservice.seviceClient.FinanceEmployeeServiceClient;
import org.handler.CustomExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    protected final EmployeeServiceClient employeeServiceClient;
    protected final FinanceEmployeeServiceClient financeEmployeeServiceClient;

    public ReportService(EmployeeServiceClient employeeServiceClient,
                         FinanceEmployeeServiceClient financeEmployeeServiceClient) {
        this.employeeServiceClient = employeeServiceClient;
        this.financeEmployeeServiceClient = financeEmployeeServiceClient;
    }

    // Метод GetEmployee для получения данных о сотруднике по идентификатору
    public EmployeeDTO getEmployee(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Вызов метода getEmployee(id) из employeeServiceClient для получения данных о сотруднике
        return employeeServiceClient.getEmployee(id);
    }

    // Метод GetPayRollCard для получения данных о заработной плате сотрудника по идентификатору
    public List<PayRollCardDTO> getPayRollCard(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Вызов метода getFinanceEmployee(id) из financeEmployeeServiceClient для получения данных о заработной плате
        return financeEmployeeServiceClient.getFinanceEmployee(id);
    }
}

