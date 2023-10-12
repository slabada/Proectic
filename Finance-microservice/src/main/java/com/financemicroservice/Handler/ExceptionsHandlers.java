package com.financemicroservice.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;

// Этот класс представляет обработчики исключений, которые будут использоваться в микросервисе.

@RestControllerAdvice
public class ExceptionsHandlers {
    @ExceptionHandler(CustomExceptions.InvalidIdException.class)
    public ResponseEntity<?> InvalidIdException(CustomExceptions.InvalidIdException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.TaxBenefitAlreadyExistsException.class)
    public ResponseEntity<?> TaxBenefitAlreadyExistsException(CustomExceptions.TaxBenefitAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.TaxBenefitNotFoundException.class)
    public ResponseEntity<?> TaxBenefitNotFoundException(CustomExceptions.TaxBenefitNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.TaxRateNotFoundException.class)
    public ResponseEntity<?> TaxRateNotFoundException(CustomExceptions.TaxRateNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.TaxRateAlreadyExistsException.class)
    public ResponseEntity<?> TaxRateAlreadyExistsException(CustomExceptions.TaxRateAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<?> WepClientNotFound(WebClientResponseException.NotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<?> WepClientBadRequest(WebClientResponseException.BadRequest ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(CustomExceptions.NegativeSalaryException.class)
    public ResponseEntity<?> NegativeSalaryException(CustomExceptions.NegativeSalaryException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.NegativePayDayException.class)
    public ResponseEntity<?> NegativePayDayException(CustomExceptions.NegativePayDayException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.PayRollCardFoundException.class)
    public ResponseEntity<?> PayRollCardFoundException(CustomExceptions.PayRollCardFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<?> ConnectException(ConnectException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка: " + ex.getMessage());
    }

    @ExceptionHandler(CustomExceptions.EmployeeFoundException.class)
    public ResponseEntity<?> EmployeeFoundException(CustomExceptions.EmployeeFoundException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
