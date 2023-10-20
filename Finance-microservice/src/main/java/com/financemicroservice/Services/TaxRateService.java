package com.financemicroservice.services;

import com.financemicroservice.models.PayRollCardModel;
import com.financemicroservice.models.TaxRateModel;
import com.financemicroservice.repository.TaxRateRepository;
import org.handler.CustomExceptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private void createStartRate() {
        // Создание объекта начальной ставки
        TaxRateModel StartRate = new TaxRateModel(1, 13, "НДФЛ");

        // Поиск начальной ставки по имени
        Optional<TaxRateModel> taxRateDb = taxRateRepository.findByName(StartRate.getName());

        // Если начальная ставка не существует, она сохраняется
        if (taxRateDb.isEmpty()) taxRateRepository.save(StartRate);
    }

    // Метод Create для создания записи о налоговой ставке
    public Optional<TaxRateModel> create(TaxRateModel r) {

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
    public Optional<TaxRateModel> get(long id) {

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
    public Optional<TaxRateModel> put(long id, TaxRateModel r) {

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
    public void delete(long id) {

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
    public Set<TaxRateModel> check(PayRollCardModel prc) {

        Set<TaxRateModel> result = new HashSet<>();

        if (prc.getTaxRate() != null && prc.getTaxRate().size() > 0) {

            // Поиск налоговой ставки в репозитории по идентификатору
            List<Long> r = prc.getTaxRate().stream()
                    .map(TaxRateModel::getId)
                    .toList();

            List<TaxRateModel> rDb = taxRateRepository.findAllById(r);
            result.addAll(rDb);

            boolean hasNDFL = rDb.stream().map(TaxRateModel::getName).anyMatch("НДФЛ"::equals);

            if(!hasNDFL){
                Optional<TaxRateModel> NDFL = taxRateRepository.findByName("НДФЛ");
                NDFL.ifPresent(result::add);
            }
        }
        else {
            Optional<TaxRateModel> NDFL = taxRateRepository.findByName("НДФЛ");
            NDFL.ifPresent(result::add);
        }

        return result;
    }
}

