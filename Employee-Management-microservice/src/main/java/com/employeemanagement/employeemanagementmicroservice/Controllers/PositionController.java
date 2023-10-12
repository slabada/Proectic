package com.employeemanagement.employeemanagementmicroservice.Controllers;

import com.employeemanagement.employeemanagementmicroservice.Models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.Services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/position")
public class PositionController {

    protected final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    // Обработчик HTTP POST запроса для создания новой должности
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PositionModel Create(@RequestBody PositionModel p){
        return positionService.Create(p);
    }

    // Обработчик HTTP GET запроса для получения информации о должности по ее идентификатору
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PositionModel> Get(@PathVariable long id){
        return positionService.Get(id);
    }

    // Обработчик HTTP PUT запроса для обновления информации о должности по ее идентификатору
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionModel Put(@PathVariable long id,@RequestBody PositionModel position){
        return positionService.Put(id, position);
    }

    // Обработчик HTTP DELETE запроса для удаления должности по ее идентификатору
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeletePosition(@PathVariable long id){
        positionService.Delete(id);
    }
}
