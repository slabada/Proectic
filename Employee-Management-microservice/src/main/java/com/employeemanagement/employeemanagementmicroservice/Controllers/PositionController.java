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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PositionModel Create(@RequestBody PositionModel p){
        return positionService.Create(p);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PositionModel> Get(@PathVariable long id){
        return positionService.Get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionModel Put(@PathVariable long id,@RequestBody PositionModel position){
        return positionService.Put(id, position);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeletePosition(@PathVariable long id){
        positionService.Delete(id);
    }
}
