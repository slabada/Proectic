package com.employeemanagement.employeemanagementmicroservice.Services;

import com.employeemanagement.employeemanagementmicroservice.Handler.CustomExceptions;
import com.employeemanagement.employeemanagementmicroservice.Models.EmployeeModel;
import com.employeemanagement.employeemanagementmicroservice.Models.PositionModel;
import com.employeemanagement.employeemanagementmicroservice.Repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    protected final EmployeeRepository employeeRepository;
    protected final PositionService positionService;

    public EmployeeService(EmployeeRepository employeeRepository, PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
    }

    // Метод для создания нового сотрудника.
    public EmployeeModel Create(EmployeeModel e){

        // Проверка, является ли идентификатор должности валидным.
        if(e.getPosition().getId() <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о должности с использованием PositionService.
        Optional<PositionModel> p = positionService.Get(e.getPosition().getId());

        // Проверка, была ли найдена должность.
        if(p.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        // Установка найденной должности для сотрудника и сохранение его в репозитории.
        e.setPosition(p.get());
        employeeRepository.save(e);

        return e;
    }

    // Метод для получения информации о сотруднике по идентификатору.
    public Optional<EmployeeModel> Get(long id){

        // Проверка, является ли предоставленный идентификатор валидным.
        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о сотруднике с использованием репозитория.
        Optional<EmployeeModel> g = employeeRepository.findById(id);

        // Проверка, был ли найден сотрудник с данным идентификатором.
        if(g.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        return g;
    }

    // Метод для обновления информации о сотруднике.
    public EmployeeModel Put(long id, EmployeeModel e){

        // Проверка, является ли предоставленный идентификатор валидным.
        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о сотруднике с использованием репозитория.
        Optional<EmployeeModel> eDb = employeeRepository.findById(id);

        // Проверка, был ли найден сотрудник с данным идентификатором.
        if(eDb.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        // Получение информации о должности с использованием PositionService.
        Optional<PositionModel> pDb = positionService.Get(e.getPosition().getId());

        // Проверка, была ли найдена должность.
        if(pDb.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        // Установка идентификатора для сотрудника и обновление информации в репозитории.
        e.setId(id);
        e.setPosition(pDb.get());
        employeeRepository.save(e);

        return e;
    }

    // Метод для удаления сотрудника по идентификатору.
    public void Delete(long id){

        // Проверка, является ли предоставленный идентификатор валидным.
        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        // Получение информации о сотруднике с использованием репозитория.
        Optional<EmployeeModel> e = employeeRepository.findById(id);

        // Проверка, был ли найден сотрудник с данным идентификатором.
        if(e.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        // Удаление сотрудника из репозитория.
        employeeRepository.deleteById(id);
    }

    // Метод для поиска сотрудников с заданными параметрами и настройкой пагинации.
    public List<EmployeeModel> Search(EmployeeModel e, int from, int size){

        // Проверка, что параметры страницы (from и size) являются валидными.
        if(from < 0 || size <= 0) throw new CustomExceptions.InvalidPageSizeException();

        // Создание объекта PageRequest для настройки пагинации.
        PageRequest page = PageRequest.of(from, size);

        // Выполнение поиска сотрудников с использованием репозитория.
        return employeeRepository.Search(e, page);
    }
}

