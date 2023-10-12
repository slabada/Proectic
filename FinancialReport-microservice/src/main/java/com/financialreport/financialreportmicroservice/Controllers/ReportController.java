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

    // Обработчик HTTP GET-запроса по пути /report/{id}
    @GetMapping("/{id}")
    public String Get(@PathVariable long id, Model model) {

        // Вызов метода GetPayRollCard(id) сервиса reportService для получения данных о заработной плате
        List<PayRollCardDTO> prc = reportService.GetPayRollCard(id);

        // Вызов метода GetEmployee(id) сервиса reportService для получения данных о сотруднике
        EmployeeDTO e = reportService.GetEmployee(id);

        // Добавление данных о заработной плате и сотруднике в модель, чтобы передать их в представление
        model.addAttribute("PayRollCard", prc);
        model.addAttribute("Employee", e);

        // Возвращение имени представления "report", которое будет отображено пользователю
        return "report";
    }
}

