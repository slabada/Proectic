package com.financemicroservice.services;

import com.financemicroservice.DTOModels.EmployeeDTO;
import com.financemicroservice.models.PayRollCardModel;
import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.models.TaxRateModel;
import com.financemicroservice.repository.PayRollCardRepository;
import com.financemicroservice.serviceClient.EmployeeServiceClient;
import com.financemicroservice.util.TaxCalculation;
import org.handler.CustomExceptions;
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

    // Метод Create для создания записи о заработной плате
    public Optional<PayRollCardModel> create(PayRollCardModel prc) {

        // Проверка на недопустимый идентификатор
        if(prc.getEmployeeId() <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о сотруднике с помощью клиента EmployeeServiceClient
        EmployeeDTO eDTO = employeeServiceClient.getPositionById(prc.getEmployeeId());

        // Проверка на отрицательную зарплату
        if (prc.getSalary().compareTo(BigDecimal.ZERO) == 0)
            throw new CustomExceptions.NegativeSalaryException();

        // Проверка на наличие даты выплаты и равенство текущей дате
        if (prc.getPayday() == null || prc.getPayday().equals(LocalDate.now()))
            throw new CustomExceptions.NegativePayDayException();

        // Вызов сервиса для проверки налоговых льгот
        Set<TaxBenefitModel> bc = taxBenefitService.check(prc);

        // Установка налоговых льгот в объект PayRollCardModel
        prc.setTaxBenefit(bc);

        // Вызов сервиса для проверки налоговых ставок
        Set<TaxRateModel> rc = taxRateService.check(prc);

        // Установка налоговых ставок в объект PayRollCardModel
        prc.setTaxRate(rc);

        BigDecimal totalAmount;

        // Рассчет общей суммы с учетом налоговых льгот
        if (prc.getTaxBenefit() != null && prc.getTaxBenefit().size() != 0) {
            totalAmount = taxCalculation.calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate()),
                    taxCalculation.totalBenefitPercentage(prc.getTaxBenefit())
            );
        } else {
            // Рассчет общей суммы без учета налоговых льгот
            totalAmount = taxCalculation.calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate())
            );
        }

        // Установка общей суммы с округлением до 2 знаков после запятой
        prc.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_DOWN));

        // Сохранение объекта PayRollCardModel в репозитории
        payRollCardRepository.save(prc);

        // Отправка данных в Kafka
        kafkaService.sendBufferKafka(eDTO, prc.getPayday(), prc.getTotalAmount());

        return Optional.of(prc);
    }

    // Метод Get для получения записи о заработной плате по идентификатору
    public Optional<PayRollCardModel> get(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PayRollCardModel> prc = payRollCardRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (prc.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        return prc;
    }

    // Метод GetEmployeeAll для получения всех записей о заработной плате сотрудника
    public List<PayRollCardModel> getEmployeeAll(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        List<PayRollCardModel> prc = payRollCardRepository.findAllByEmployeeId(id);

        // Если записи не найдены, генерируется исключение
        if (prc.isEmpty())
            throw new CustomExceptions.EmployeeNotFoundException();

        return prc;
    }

    public PayRollCardModel put(long id, PayRollCardModel prc) {

        // Проверка на недопустимый идентификатор id
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Проверка на недопустимый идентификатор сотрудника
        if(prc.getEmployeeId() <= 0) throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<PayRollCardModel> prcDb = payRollCardRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (prcDb.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        // Проверка на отрицательную зарплату
        if (prc.getSalary().compareTo(BigDecimal.ZERO) == 0)
            throw new CustomExceptions.NegativeSalaryException();

        // Проверка на наличие даты выплаты и равенство текущей дате
        if (prc.getPayday() == null || prc.getPayday().equals(LocalDate.now()))
            throw new CustomExceptions.NegativePayDayException();

        // Вызов сервиса для проверки налоговых льгот
        Set<TaxBenefitModel> bc = taxBenefitService.check(prc);

        // Установка налоговых льгот в объект PayRollCardModel
        prc.setTaxBenefit(bc);

        // Вызов сервиса для проверки налоговых ставок
        Set<TaxRateModel> rc = taxRateService.check(prc);

        // Установка налоговых ставок в объект PayRollCardModel
        prc.setTaxRate(rc);

        BigDecimal totalAmount;

        // Рассчет общей суммы с учетом налоговых льгот
        if (prc.getTaxBenefit() != null && prc.getTaxBenefit().size() != 0) {
            totalAmount = taxCalculation.calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate()),
                    taxCalculation.totalBenefitPercentage(prc.getTaxBenefit())
            );
        } else {
            // Рассчет общей суммы без учета налоговых льгот
            totalAmount = taxCalculation.calculation(
                    prc.getSalary(),
                    taxCalculation.totalRatePercentage(prc.getTaxRate())
            );
        }

        // Установка общей суммы с округлением до 2 знаков после запятой
        prc.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_DOWN));

        // Сохранение объекта PayRollCardModel в репозитории
        prc.setId(id);
        payRollCardRepository.save(prc);

        return prc;
    }

    // Метод Delete для удаления записи о заработной плате
    public void delete(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<PayRollCardModel> prcDb = payRollCardRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (prcDb.isEmpty()) throw new CustomExceptions.PayRollCardFoundException();

        // Удаление записи по идентификатору
        payRollCardRepository.deleteById(id);
    }
}

