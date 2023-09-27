package com.financemicroservice.Repositorys;

import com.financemicroservice.Models.TaxRateModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateRepository extends BaseRepository<TaxRateModel, Long> {
}
