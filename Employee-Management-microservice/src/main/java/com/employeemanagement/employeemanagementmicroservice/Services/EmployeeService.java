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

    public EmployeeModel Create(EmployeeModel e){

        if(e.getPosition().getId() <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PositionModel> p = positionService.Get(e.getPosition().getId());

        if(p.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        e.setPosition(p.get());

        employeeRepository.save(e);

        return e;
    }

    public Optional<EmployeeModel> Get(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<EmployeeModel> g = employeeRepository.findById(id);

        if(g.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        return g;
    }

    public EmployeeModel Put(long id, EmployeeModel e){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<EmployeeModel> eDb = employeeRepository.findById(id);

        if(eDb.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        Optional<PositionModel> pDb = positionService.Get(e.getPosition().getId());

        if(pDb.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        e.setId(id);
        e.setPosition(pDb.get());

        employeeRepository.save(e);

        return e;
    }

    public void Delete(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<EmployeeModel> e = employeeRepository.findById(id);

        if(e.isEmpty()) throw new CustomExceptions.EmployeeNotFoundException();

        employeeRepository.deleteById(id);
    }

    public List<EmployeeModel> Search(EmployeeModel e, int from, int size){

        if(from < 0 || size <= 0) throw new CustomExceptions.InvalidPageSizeException();

        PageRequest page = PageRequest.of(from,size);

        return employeeRepository.Search(e, page);
    }
}
