package com.financemicroservice.controllers;

import com.financemicroservice.models.TaxRateModel;
import com.financemicroservice.services.TaxRateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rate")
public class TaxRateController {

    protected final TaxRateService taxRateService;

    public TaxRateController(TaxRateService taxRateService) {
        this.taxRateService = taxRateService;
    }

    // Метод для создания новой налоговой ставки.
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<TaxRateModel> create(@RequestBody TaxRateModel r){
        return taxRateService.create(r);
    }

    // Метод для получения информации о налоговой ставке по идентификатору.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxRateModel> get(@PathVariable long id){
        return taxRateService.get(id);
    }

    // Метод для обновления информации о налоговой ставке по идентификатору.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxRateModel> put(@PathVariable long id, @RequestBody TaxRateModel r){
        return taxRateService.put(id, r);
    }

    // Метод для удаления налоговой ставки по идентификатору.
    @DeleteMapping("{id}")
    public void deleteBenefit(@PathVariable long id){
        taxRateService.delete(id);
    }
}

