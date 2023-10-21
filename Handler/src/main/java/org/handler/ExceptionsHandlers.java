package org.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Этот класс представляет обработчики исключений, которые будут использоваться в микросервисе.
@RestControllerAdvice
public class ExceptionsHandlers {

    @ExceptionHandler({
            CustomExceptions.InvalidIdException.class,
            CustomExceptions.InvalidPageSizeException.class,
            CustomExceptions.TaxBenefitAlreadyExistsException.class,
            CustomExceptions.TaxRateAlreadyExistsException.class,
            WebClientResponseException.BadRequest.class,
            CustomExceptions.NegativeSalaryException.class,
            CustomExceptions.NegativePayDayException.class,
    })
    public ResponseEntity<DTOError> handleBadRequest(Exception ex) {
        DTOError error = new DTOError(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> feignExceptionNotFound(FeignException ex) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(ex.contentUTF8());
        String message = responseJson.get("message").asText();

        DTOError error = new DTOError(
                message,
                LocalDateTime.now(),
                ex.status()
        );
        return ResponseEntity.status(ex.status()).body(error);
    }

    @ExceptionHandler({
            CustomExceptions.PositionNotFoundException.class,
            CustomExceptions.EmployeeNotFoundException.class,
            CustomExceptions.TaxBenefitNotFoundException.class,
            CustomExceptions.TaxRateNotFoundException.class,
            WebClientResponseException.NotFound.class,
            CustomExceptions.PayRollCardFoundException.class
    })
    public ResponseEntity<?> handleNotFound(Exception ex) {
        DTOError error = new DTOError(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CustomExceptions.PositionAlreadyExistsException.class)
    public ResponseEntity<?> handleConflict(CustomExceptions.PositionAlreadyExistsException ex) {
        DTOError error = new DTOError(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
