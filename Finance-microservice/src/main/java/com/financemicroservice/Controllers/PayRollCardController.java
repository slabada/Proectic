package com.financemicroservice.Controllers;

import com.financemicroservice.Models.PayRollCardModel;
import com.financemicroservice.Services.PayRollCardService;
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
    public Optional<PayRollCardModel> Create(@RequestBody PayRollCardModel prc){
        return payRollCardService.Create(prc);
    }

    // Метод для получения информации о записи о выплате по идентификатору.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PayRollCardModel> Get(@PathVariable long id){
        return payRollCardService.Get(id);
    }

    // Метод для получения списка всех записией о выплате для указанного сотрудника.
    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PayRollCardModel> GetEmployeeAll(@PathVariable long id){
        return payRollCardService.GetEmployeeAll(id);
    }

    // Метод для обновления информации записи о выплате.
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PayRollCardModel Put(@PathVariable long id, @RequestBody PayRollCardModel prc){
        return payRollCardService.Put(id, prc);
    }

    // Метод для удаления записи о выплате по идентификатору.
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void Delete(@PathVariable long id){
        payRollCardService.Delete(id);
    }
}

