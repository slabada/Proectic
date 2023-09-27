package com.financemicroservice.Controllers;

import com.financemicroservice.Models.TaxRateModel;
import com.financemicroservice.Services.TaxRateService;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<TaxRateModel> Create(@RequestBody TaxRateModel r){
        return taxRateService.Create(r);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxRateModel> Get(@PathVariable long id){
        return taxRateService.Get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TaxRateModel> Put(@PathVariable long id,
                                      @RequestBody TaxRateModel r){
        return taxRateService.Put(id,r);
    }

    @DeleteMapping("{id}")
    public void DeleteBenefit(@PathVariable long id){
        taxRateService.Delete(id);
    }
}
