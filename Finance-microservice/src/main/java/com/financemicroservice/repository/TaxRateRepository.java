package com.financemicroservice.repository;

import com.financemicroservice.models.TaxRateModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateRepository extends BaseRepository<TaxRateModel, Long> {
}
