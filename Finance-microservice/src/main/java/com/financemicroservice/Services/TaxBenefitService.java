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

    // Метод Create для создания записи о налоговых льготах
    public TaxBenefitModel Create(TaxBenefitModel b) {

        // Поиск записи по имени налоговой льготы
        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findByName(b.getName());

        // Если запись уже существует, генерируется исключение
        if (bDb.isPresent())
            throw new CustomExceptions.InvalidIdException();

        // Сохранение новой записи
        taxBenefitRepository.save(b);

        return b;
    }

    // Метод Get для получения записи о налоговой льготе по идентификатору
    public Optional<TaxBenefitModel> Get(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (bDb.isEmpty())
            throw new CustomExceptions.TaxBenefitNotFoundException();

        return bDb;
    }

    // Метод Put для обновления записи о налоговой льготе
    public Optional<TaxBenefitModel> Put(long id, TaxBenefitModel b) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (bDb.isEmpty())
            throw new CustomExceptions.TaxBenefitNotFoundException();

        // Поиск записи по имени налоговой льготы
        Optional<TaxBenefitModel> benefitName = taxBenefitRepository.findByName(b.getName());

        // Если запись с таким именем уже существует, генерируется исключение
        if (benefitName.isPresent())
            throw new CustomExceptions.TaxBenefitAlreadyExistsException();

        // Установка идентификатора и сохранение обновленной записи
        b.setId(id);
        taxBenefitRepository.save(b);

        return Optional.of(b);
    }

    // Метод Delete для удаления записи о налоговой льготе
    public void Delete(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (bDb.isEmpty())
            throw new CustomExceptions.TaxBenefitNotFoundException();

        // Удаление записи по идентификатору
        taxBenefitRepository.deleteById(id);
    }

    // Метод Check для проверки налоговых льгот в объекте PayRollCardModel
    public Set<TaxBenefitModel> Check(PayRollCardModel prc) {
        Set<TaxBenefitModel> result = new HashSet<>();
        if (prc.getTaxBenefit() != null) {
            for (TaxBenefitModel b : prc.getTaxBenefit()) {
                // Поиск налоговой льготы в репозитории по идентификатору
                Optional<TaxBenefitModel> bDb = taxBenefitRepository.findById(b.getId());
                bDb.ifPresent(result::add);
            }
        }
        return result;
    }
}

