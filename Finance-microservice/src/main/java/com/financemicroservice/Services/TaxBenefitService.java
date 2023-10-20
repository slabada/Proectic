package com.financemicroservice.services;

import com.financemicroservice.models.PayRollCardModel;
import com.financemicroservice.models.TaxBenefitModel;
import com.financemicroservice.repository.TaxBenefitRepository;
import org.handler.CustomExceptions;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaxBenefitService {

    protected final TaxBenefitRepository taxBenefitRepository;

    public TaxBenefitService(TaxBenefitRepository taxBenefitRepository) {
        this.taxBenefitRepository = taxBenefitRepository;
    }

    // Метод Create для создания записи о налоговых льготах
    public TaxBenefitModel create(TaxBenefitModel b) {

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
    public Optional<TaxBenefitModel> get(long id) {

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
    public Optional<TaxBenefitModel> put(long id, TaxBenefitModel b) {

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
    public void delete(long id) {

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
    public Set<TaxBenefitModel> check(PayRollCardModel prc) {

        Set<TaxBenefitModel> result = new HashSet<>();

        if (prc.getTaxBenefit() != null) {

            List<Long> b = prc.getTaxBenefit().stream()
                    .map(TaxBenefitModel::getId)
                    .toList();

            List<TaxBenefitModel> bDb = taxBenefitRepository.findAllById(b);
            result.addAll(bDb);
        }

        return result;
    }
}

