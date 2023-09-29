package com.financemicroservice.Services;

import com.financemicroservice.Handler.CustomExceptions;
import com.financemicroservice.Models.PayRollCardModel;
import com.financemicroservice.Models.TaxRateModel;
import com.financemicroservice.Repositorys.TaxRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaxRateService {

    protected final TaxRateRepository taxRateRepository;

    public TaxRateService(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;
    }

    @Bean
    private void CreateStartRate(){
        TaxRateModel StartRate = new TaxRateModel(1, 13, "НДФЛ");
        Optional<TaxRateModel> taxRateDb = taxRateRepository.findByName(StartRate.getName());
        if(taxRateDb.isEmpty()) taxRateRepository.save(StartRate);
    }

    public Optional<TaxRateModel> Create(TaxRateModel r){

        Optional<TaxRateModel> taxRateDb = taxRateRepository.findByName(r.getName());

        if(taxRateDb.isPresent()) throw new CustomExceptions.TaxRateNotFoundException();

        taxRateRepository.save(r);

        return Optional.of(r);
    }

    public Optional<TaxRateModel> Get(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        if(rDb.isEmpty()) throw new CustomExceptions.TaxRateNotFoundException();

        return rDb;
    }

    public Optional<TaxRateModel> Put(long id, TaxRateModel r){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        if(rDb.isEmpty()) throw new CustomExceptions.TaxRateNotFoundException();

        Optional<TaxRateModel> TaxRateName = taxRateRepository.findByName(r.getName());

        if(TaxRateName.isPresent()) throw new CustomExceptions.TaxRateAlreadyExistsException();

        r.setId(id);

        taxRateRepository.save(r);

        return Optional.of(r);
    }

    public void Delete(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        if(rDb.isEmpty()) throw new CustomExceptions.TaxRateNotFoundException();

        taxRateRepository.deleteById(id);
    }

    public Set<TaxRateModel> Check(PayRollCardModel prc){
        Set<TaxRateModel> result = new HashSet<>();
        if(prc.getTaxRate() != null && prc.getTaxRate().size() > 0){
            for(TaxRateModel r : prc.getTaxRate()){
                Optional<TaxRateModel> rDb = taxRateRepository.findById(r.getId());
                rDb.ifPresent(result::add);
            }
        }
        else {
            Optional<TaxRateModel> rDb = taxRateRepository.findByName("НДФЛ");
            rDb.ifPresent(result::add);
        }
        return result;
    }
}
