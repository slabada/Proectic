package com.financemicroservice.Repositorys;

import com.financemicroservice.Models.PayRollCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayRollCardRepository extends JpaRepository<PayRollCardModel, Long> {
    List<PayRollCardModel> findAllByEmployeeId(long id);

    Optional<PayRollCardModel> findByEmployeeId(long id);
}
