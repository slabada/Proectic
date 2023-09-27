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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxBenefitModel Create(@RequestBody TaxBenefitModel b){
        return taxBenefitService.Create(b);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> Get(@PathVariable long id){
        return taxBenefitService.Get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxBenefitModel> Put(@PathVariable long id,@RequestBody TaxBenefitModel b){
        return taxBenefitService.Put(id, b);
    }

    @DeleteMapping("{id}")
    public void Delete(@PathVariable long id){
        taxBenefitService.Delete(id);
    }
}
