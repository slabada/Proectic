package com.employeemanagement.employeemanagementmicroservice.Handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandlers {

    @ExceptionHandler(CustomExceptions.InvalidIdException.class)
    public ResponseEntity<?> InvalidIdException(CustomExceptions.InvalidIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.PositionNotFoundException.class)
    public ResponseEntity<?> PositionNotFoundException(CustomExceptions.PositionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.EmployeeNotFoundException.class)
    public ResponseEntity<?> EmployeeNotFoundException(CustomExceptions.EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.PositionAlreadyExistsException.class)
    public ResponseEntity<?> PositionAlreadyExistsException(CustomExceptions.PositionAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.InvalidPageSizeException.class)
    public ResponseEntity<?> InvalidPageSizeException(CustomExceptions.InvalidPageSizeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
