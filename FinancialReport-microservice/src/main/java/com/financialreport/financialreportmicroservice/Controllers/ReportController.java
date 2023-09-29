package com.financialreport.financialreportmicroservice.Controllers;

import com.financialreport.financialreportmicroservice.DTO.DTOModels.EmployeeDTO;
import com.financialreport.financialreportmicroservice.DTO.DTOModels.PayRollCardDTO;
import com.financialreport.financialreportmicroservice.Service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    protected final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public String Get(@PathVariable long id, Model model) {

        List<PayRollCardDTO> prc = reportService.GetPayRollCard(id);

        EmployeeDTO e = reportService.GetEmployee(id);

        model.addAttribute("PayRollCard", prc);

        model.addAttribute("Employee", e);

        return "report";
    }
}
