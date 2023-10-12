package com.financialreport.financialreportmicroservice.Service;

import com.financialreport.financialreportmicroservice.DTO.DTOModels.EmployeeDTO;
import com.financialreport.financialreportmicroservice.DTO.DTOModels.PayRollCardDTO;
import com.financialreport.financialreportmicroservice.DTO.SeviceClient.EmployeeServiceClient;
import com.financialreport.financialreportmicroservice.DTO.SeviceClient.FinanceEmployeeServiceClient;
import com.financialreport.financialreportmicroservice.Handler.CustomExceptions;
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
    public EmployeeDTO GetEmployee(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Вызов метода getEmployee(id) из employeeServiceClient для получения данных о сотруднике
        return employeeServiceClient.getEmployee(id);
    }

    // Метод GetPayRollCard для получения данных о заработной плате сотрудника по идентификатору
    public List<PayRollCardDTO> GetPayRollCard(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Вызов метода getFinanceEmployee(id) из financeEmployeeServiceClient для получения данных о заработной плате
        return financeEmployeeServiceClient.getFinanceEmployee(id);
    }
}

