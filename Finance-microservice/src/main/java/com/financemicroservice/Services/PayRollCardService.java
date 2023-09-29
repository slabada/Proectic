package com.financemicroservice.Services;

import com.financemicroservice.DTO.DTOModels.EmployeeDTO;
import com.financemicroservice.DTO.DTOModels.PositionDTO;
import com.financemicroservice.DTO.ServiceClient.EmployeeServiceClient;
import com.financemicroservice.Handler.CustomExceptions;
import com.financemicroservice.Models.PayRollCardModel;
import com.financemicroservice.Models.TaxBenefitModel;
import com.financemicroservice.Models.TaxRateModel;
import com.financemicroservice.Repositorys.PayRollCardRepository;
import com.financemicroservice.Util.TaxCalculation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PayRollCardService {

    protected final PayRollCardRepository payRollCardRepository;

    protected final TaxBenefitService taxBenefitService;

    protected final TaxRateService taxRateService;

    protected final KafkaService kafkaService;

    protected final TaxCalculation taxCalculation;

    protected final EmployeeServiceClient employeeServiceClient;

    public PayRollCardService(PayRollCardRepository payRollCardRepository,
                              TaxBenefitService taxBenefitService,
                              TaxRateService taxRateService,
                              KafkaService kafkaService,
                              TaxCalculation taxCalculation,
                              EmployeeServiceClient employeeServiceClient) {

        this.payRollCardRepository = payRollCardRepository;
        this.taxBenefitService = taxBenefitService;
        this.taxRateService = taxRateService;
        this.kafkaService = kafkaService;
        this.taxCalculation = taxCalculation;
        this.employeeServiceClient = employeeServiceClient;
    }

    public Optional<PayRollCardModel> Create(PayRollCardModel prc) {

        if(prc.getSalary().compareTo(BigDecimal.ZERO) == 0)
            throw new CustomExceptions.NegativeSalaryException();

        if(prc.getPayday() == null || prc.getPayday().equals(LocalDate.now()))
            throw new CustomExceptions.NegativePayDayException();

        Set<TaxBenefitModel> bc = taxBenefitService.Check(prc);

        prc.setTaxBenefit(bc);

        Set<TaxRateModel> rc = taxRateService.Check(prc);

        prc.setTaxRate(rc);

        BigDecimal totalAmount;
        if(prc.getTaxBenefit() != null && prc.getTaxBenefit().size() != 0){
            totalAmount = taxCalculation.Calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate()),
                    taxCalculation.totalBenefitPercentage(prc.getTaxBenefit())
            );
        }
        else{
            totalAmount = taxCalculation.Calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate())
            );
        }

        prc.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_DOWN));

        payRollCardRepository.save(prc);

        EmployeeDTO eDTO = employeeServiceClient.getPositionById(prc.getEmployeeId());

        kafkaService.SendBufferKafka(eDTO, prc.getPayday(), prc.getTotalAmount());

        return Optional.of(prc);
    }

    public Optional<PayRollCardModel> Get(long id){

        Optional<PayRollCardModel> prc = payRollCardRepository.findById(id);

        if(prc.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        return prc;
    }

    public List<PayRollCardModel> GetEmployeeAll(long id){

        List<PayRollCardModel> prc = payRollCardRepository.findAllByEmployeeId(id);

        if(prc.isEmpty()) throw new CustomExceptions.EmployeeFoundException();

        return prc;
    }

    public PayRollCardModel Put(long id, PayRollCardModel prc){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PayRollCardModel> prcDb = payRollCardRepository.findById(id);

        if(prcDb.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        prc.setId(id);
        payRollCardRepository.save(prc);

        return prc;
    }

    public void Delete(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PayRollCardModel> prcDb = payRollCardRepository.findById(id);

        if(prcDb.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        payRollCardRepository.deleteById(id);
    }
}
