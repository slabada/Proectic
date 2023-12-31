package com.employeemanagement.employeemanagementmicroservice.controllers;

import com.employeemanagement.employeemanagementmicroservice.models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.services.PositionService;
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
    public PositionModel create(@RequestBody PositionModel p){
        return positionService.create(p);
    }

    // Обработчик HTTP GET запроса для получения информации о должности по ее идентификатору
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PositionModel> get(@PathVariable long id){
        return positionService.get(id);
    }

    // Обработчик HTTP PUT запроса для обновления информации о должности по ее идентификатору
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionModel put(@PathVariable long id,@RequestBody PositionModel position){
        return positionService.put(id, position);
    }

    // Обработчик HTTP DELETE запроса для удаления должности по ее идентификатору
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePosition(@PathVariable long id){
        positionService.delete(id);
    }
}
