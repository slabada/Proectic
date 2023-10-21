package com.financemicroservice.controllers;

import com.financemicroservice.models.PayRollCardModel;
import com.financemicroservice.services.PayRollCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salary")
public class PayRollCardController {

    protected final PayRollCardService payRollCardService;

    public PayRollCardController(PayRollCardService payRollCardService) {
        this.payRollCardService = payRollCardService;
    }

    // Метод для создания новой записи о выплате.
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PayRollCardModel> create(@RequestBody PayRollCardModel prc){
        return payRollCardService.create(prc);
    }

    // Метод для получения информации о записи о выплате по идентификатору.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PayRollCardModel> get(@PathVariable long id){
        return payRollCardService.get(id);
    }

    // Метод для получения списка всех записией о выплате для указанного сотрудника.
    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PayRollCardModel> getEmployeeAll(@PathVariable long id){
        return payRollCardService.getEmployeeAll(id);
    }

    // Метод для обновления информации записи о выплате.
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PayRollCardModel put(@PathVariable long id, @RequestBody PayRollCardModel prc){
        return payRollCardService.put(id, prc);
    }

    // Метод для удаления записи о выплате по идентификатору.
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id){
        payRollCardService.delete(id);
    }
}

