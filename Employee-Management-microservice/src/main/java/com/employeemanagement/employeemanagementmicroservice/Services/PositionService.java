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

    public PositionModel Create(PositionModel p){

        boolean pByName = positionRepository.existsByName(p.getName());

        if(pByName) throw new CustomExceptions.PositionAlreadyExistsException();

        positionRepository.save(p);

        return p;
    }

    public Optional<PositionModel> Get(long id){

        if(id <= 0 ) throw new CustomExceptions.InvalidIdException();

        Optional<PositionModel> p = positionRepository.findById(id);

        if(p.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        return p;
    }

    public PositionModel Put(long id,PositionModel p){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PositionModel> pDb = positionRepository.findById(id);

        if(pDb.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        positionRepository.save(p);

        return p;
    }

    public void Delete(long id){

        if(id <= 0) throw new CustomExceptions.InvalidIdException();

        Optional<PositionModel> Delete = positionRepository.findById(id);

        if(Delete.isEmpty()) throw new CustomExceptions.PositionNotFoundException();

        positionRepository.deleteById(id);
    }
}
