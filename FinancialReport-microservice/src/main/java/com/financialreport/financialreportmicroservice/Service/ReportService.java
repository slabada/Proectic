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

    public EmployeeDTO GetEmployee(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        return employeeServiceClient.getEmployee(id);
    }

    public List<PayRollCardDTO> GetPayRollCard(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        return financeEmployeeServiceClient.getFinanceEmployee(id);
    }
}
