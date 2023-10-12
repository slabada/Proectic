package com.financemicroservice.Controllers;

import com.financemicroservice.Models.TaxBenefitModel;
import com.financemicroservice.Services.TaxBenefitService;
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
    public TaxBenefitModel Create(@RequestBody TaxBenefitModel b){
        return taxBenefitService.Create(b);
    }

    // Метод для получения информации о налоговой льготе по идентификатору.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> Get(@PathVariable long id){
        return taxBenefitService.Get(id);
    }

    // Метод для обновления информации о налоговой льготе по идентификатору.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> Put(@PathVariable long id, @RequestBody TaxBenefitModel b){
        return taxBenefitService.Put(id, b);
    }

    // Метод для удаления налоговой льготы по идентификатору.
    @DeleteMapping("{id}")
    public void Delete(@PathVariable long id){
        taxBenefitService.Delete(id);
    }
}

