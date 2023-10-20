package com.financemicroservice.repository;

import com.financemicroservice.models.TaxBenefitModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBenefitRepository extends BaseRepository<TaxBenefitModel, Long> {
}
