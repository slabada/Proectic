package com.financialreport.financialreportmicroservice.Handler;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Этот класс представляет обработчики исключений, которые будут использоваться в микросервисе.

@RestControllerAdvice
public class ExceptionsHandlers {
    @ExceptionHandler(CustomExceptions.InvalidIdException.class)
    public ResponseEntity<String> InvalidIdException(CustomExceptions.InvalidIdException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> InvalidIdException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Работник не найден");
    }
}
