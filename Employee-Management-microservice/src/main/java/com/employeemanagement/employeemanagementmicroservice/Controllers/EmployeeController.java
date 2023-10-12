package com.employeemanagement.employeemanagementmicroservice.Controllers;

import com.employeemanagement.employeemanagementmicroservice.Models.EmployeeModel;
import com.employeemanagement.employeemanagementmicroservice.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    protected final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Обработчик HTTP POST запроса для создания нового сотрудника
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeModel Create(@Valid @RequestBody EmployeeModel employee){
        return employeeService.Create(employee);
    }

    // Обработчик HTTP GET запроса для получения информации о сотруднике по его идентификатору
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<EmployeeModel> Get(@PathVariable long id){
        return employeeService.Get(id);
    }

    // Обработчик HTTP PUT запроса для обновления информации о сотруднике по его идентификатору
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel Put(@PathVariable long id,
                             @RequestBody EmployeeModel employee){
        return employeeService.Put(id,employee);
    }

    // Обработчик HTTP DELETE запроса для удаления сотрудника по его идентификатору
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void Delete(@PathVariable long id){
        employeeService.Delete(id);
    }

    // Обработчик HTTP GET запроса для поиска сотрудников с заданными параметрами
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeModel> Search(@ModelAttribute EmployeeModel e,
                                      @RequestParam(defaultValue = "0") int from,
                                      @RequestParam(defaultValue = "10") int size){
        return employeeService.Search(e,from,size);
    }
}
