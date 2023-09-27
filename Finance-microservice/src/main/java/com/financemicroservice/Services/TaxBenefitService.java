package com.financemicroservice.Services;

import com.financemicroservice.Handler.CustomExceptions;
import com.financemicroservice.Models.PayRollCardModel;
import com.financemicroservice.Models.TaxBenefitModel;
import com.financemicroservice.Repositorys.TaxBenefitRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaxBenefitService {

    protected final TaxBenefitRepository taxBenefitRepository;

    public TaxBenefitService(TaxBenefitRepository taxBenefitRepository) {
        this.taxBenefitRepository = taxBenefitRepository;
    }

    public TaxBenefitModel Create(TaxBenefitModel b){

        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findByName(b.getName());

        if(bDb.isPresent()) throw new CustomExceptions.InvalidIdException();

        taxBenefitRepository.save(b);

        return b;
    }

    public Optional<TaxBenefitModel> Get(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        if(bDb.isEmpty()) throw new CustomExceptions.TaxBenefitNotFoundException();

        return bDb;
    }

    public Optional<TaxBenefitModel> Put(long id, TaxBenefitModel b){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        if(bDb.isEmpty()) throw new CustomExceptions.TaxBenefitNotFoundException();

        Optional<TaxBenefitModel> benefitName = taxBenefitRepository.findByName(b.getName());

        if(benefitName.isPresent()) throw new CustomExceptions.TaxBenefitAlreadyExistsException();

        b.setId(id);

        taxBenefitRepository.save(b);

        return Optional.of(b);
    }

    public void Delete(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        if(bDb.isEmpty()) throw new CustomExceptions.TaxBenefitNotFoundException();

        taxBenefitRepository.deleteById(id);
    }

    public Set<TaxBenefitModel> Check(PayRollCardModel prc){
        Set<TaxBenefitModel> result = new HashSet<>();
        if(prc.getTaxBenefit() != null){
            for(TaxBenefitModel b : prc.getTaxBenefit()){
                Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(b.getId());
                bDb.ifPresent(result::add);
            }
        }
        return result;
    }
}
