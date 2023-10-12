package com.employeemanagement.employeemanagementmicroservice.Services;

import com.employeemanagement.employeemanagementmicroservice.Handler.CustomExceptions;
import com.employeemanagement.employeemanagementmicroservice.Models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.Repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {

    protected final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    // Метод для создания новой должности.
    public PositionModel Create(PositionModel p) {

        // Проверка, существует ли должность с таким именем.
        boolean pByName = positionRepository.existsByName(p.getName());

        // Если должность с таким именем уже существует, выбрасываем исключение.
        if (pByName) throw new CustomExceptions.PositionAlreadyExistsException();

        // Сохранение новой должности в репозитории.
        positionRepository.save(p);

        return p;
    }

    // Метод для получения информации о должности по идентификатору.
    public Optional<PositionModel> Get(long id) {

        // Проверка, является ли предоставленный идентификатор валидным.
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о должности с использованием репозитория.
        Optional<PositionModel> p = positionRepository.findById(id);

        // Проверка, была ли найдена должность с данным идентификатором.
        if (p.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        return p;
    }

    // Метод для обновления информации о должности.
    public PositionModel Put(long id, PositionModel p) {

        // Проверка, является ли предоставленный идентификатор валидным.
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о существующей должности с использованием репозитория.
        Optional<PositionModel> pDb = positionRepository.findById(id);

        // Проверка, была ли найдена должность с данным идентификатором.
        if (pDb.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        // Установка идентификатора для должности и обновление информации в репозитории.
        p.setId(id);
        positionRepository.save(p);

        return p;
    }

    // Метод для удаления должности по идентификатору.
    public void Delete(long id) {

        // Проверка, является ли предоставленный идентификатор валидным.
        if (id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о должности с использованием репозитория.
        Optional<PositionModel> Delete = positionRepository.findById(id);

        // Проверка, была ли найдена должность с данным идентификатором.
        if (Delete.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        // Удаление должности из репозитория.
        positionRepository.deleteById(id);
    }
}

