package com.financialreport.financialreportmicroservice.Handler;

// Этот класс определяет пользовательские исключения, которые могут возникнуть в микросервисе.

public class CustomExceptions {
    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException() {
            super("Предоставлен неверный идентификатор.");
        }
    }
}
