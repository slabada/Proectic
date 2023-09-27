package com.financemicroservice.Repositorys;

import com.financemicroservice.Models.TaxBenefitModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBenefitRepository extends BaseRepository<TaxBenefitModel, Long> {
}
