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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PayRollCardModel> Create(@RequestBody PayRollCardModel prc){
        return payRollCardService.Create(prc);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PayRollCardModel> Get(@PathVariable long id){
        return payRollCardService.Get(id);
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PayRollCardModel> GetEmployeeAll(@PathVariable long id){
        return payRollCardService.GetEmployeeAll(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PayRollCardModel Put(@PathVariable long id, @RequestBody PayRollCardModel prc){
        return payRollCardService.Put(id, prc);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void Delete(@PathVariable long id){
        payRollCardService.Delete(id);
    }
}
