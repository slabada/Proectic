package com.financemicroservice.controllers;

import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.services.TaxBenefitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/benefit")
public class TaxBenefitController {

    protected final TaxBenefitService taxBenefitService;

    public TaxBenefitController(TaxBenefitService taxBenefitService) {
        this.taxBenefitService = taxBenefitService;
    }

    // Метод для создания новой налоговой льготы.
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxBenefitModel create(@RequestBody TaxBenefitModel b){
        return taxBenefitService.create(b);
    }

    // Метод для получения информации о налоговой льготе по идентификатору.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> get(@PathVariable long id){
        return taxBenefitService.get(id);
    }

    // Метод для обновления информации о налоговой льготе по идентификатору.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> put(@PathVariable long id, @RequestBody TaxBenefitModel b){
        return taxBenefitService.put(id, b);
    }

    // Метод для удаления налоговой льготы по идентификатору.
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id){
        taxBenefitService.delete(id);
    }
}

