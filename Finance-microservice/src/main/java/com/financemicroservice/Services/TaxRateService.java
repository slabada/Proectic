package com.financemicroservice.Services;

import com.financemicroservice.Handler.CustomExceptions;
import com.financemicroservice.Models.PayRollCardModel;
import com.financemicroservice.Models.TaxRateModel;
import com.financemicroservice.Repositorys.TaxRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaxRateService {

    // Поле класса, представляющее зависимость сервиса
    protected final TaxRateRepository taxRateRepository;

    // Конструктор класса, используется для внедрения зависимости
    public TaxRateService(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;
    }

    // Метод CreateStartRate для создания начальной ставки
    @Bean
    private void CreateStartRate() {
        // Создание объекта начальной ставки
        TaxRateModel StartRate = new TaxRateModel(1, 13, "НДФЛ");

        // Поиск начальной ставки по имени
        Optional<TaxRateModel> taxRateDb = taxRateRepository.findByName(StartRate.getName());

        // Если начальная ставка не существует, она сохраняется
        if (taxRateDb.isEmpty())
            taxRateRepository.save(StartRate);
    }

    // Метод Create для создания записи о налоговой ставке
    public Optional<TaxRateModel> Create(TaxRateModel r) {

        // Поиск записи по имени налоговой ставки
        Optional<TaxRateModel> taxRateDb = taxRateRepository.findByName(r.getName());

        // Если запись с таким именем уже существует, генерируется исключение
        if (taxRateDb.isPresent())
            throw new CustomExceptions.TaxRateNotFoundException();

        // Сохранение новой записи
        taxRateRepository.save(r);

        return Optional.of(r);
    }

    // Метод Get для получения записи о налоговой ставке по идентификатору
    public Optional<TaxRateModel> Get(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (rDb.isEmpty())
            throw new CustomExceptions.TaxRateNotFoundException();

        return rDb;
    }

    // Метод Put для обновления записи о налоговой ставке
    public Optional<TaxRateModel> Put(long id, TaxRateModel r) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (rDb.isEmpty())
            throw new CustomExceptions.TaxRateNotFoundException();

        // Поиск записи по имени налоговой ставки
        Optional<TaxRateModel> TaxRateName = taxRateRepository.findByName(r.getName());

        // Если запись с таким именем уже существует, генерируется исключение
        if (TaxRateName.isPresent())
            throw new CustomExceptions.TaxRateAlreadyExistsException();

        // Установка идентификатора и сохранение обновленной записи
        r.setId(id);
        taxRateRepository.save(r);

        return Optional.of(r);
    }

    // Метод Delete для удаления записи о налоговой ставке
    public void Delete(long id) {

        // Проверка на недопустимый идентификатор
        if (id <= 0)
            throw new CustomExceptions.InvalidIdException();

        // Поиск записи по идентификатору
        Optional<TaxRateModel> rDb = taxRateRepository.findById(id);

        // Если запись не найдена, генерируется исключение
        if (rDb.isEmpty())
            throw new CustomExceptions.TaxRateNotFoundException();

        // Удаление записи по идентификатору
        taxRateRepository.deleteById(id);
    }

    // Метод Check для проверки налоговых ставок в объекте PayRollCardModel
    public Set<TaxRateModel> Check(PayRollCardModel prc) {
        Set<TaxRateModel> result = new HashSet<>();
        if (prc.getTaxRate() != null && prc.getTaxRate().size() > 0) {
            for (TaxRateModel r : prc.getTaxRate()) {
                // Поиск налоговой ставки в репозитории по идентификатору
                Optional<TaxRateModel> rDb = taxRateRepository.findById(r.getId());
                rDb.ifPresent(result::add);
            }
        } else {
            // Если налоговые ставки не указаны, используется "НДФЛ"
            Optional<TaxRateModel> rDb = taxRateRepository.findByName("НДФЛ");
            rDb.ifPresent(result::add);
        }
        return result;
    }
}

