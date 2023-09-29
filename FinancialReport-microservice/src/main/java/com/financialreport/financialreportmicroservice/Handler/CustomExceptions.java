package com.financialreport.financialreportmicroservice.Handler;

import javax.management.openmbean.InvalidKeyException;

public class CustomExceptions {
    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException() {
            super("Предоставлен неверный идентификатор.");
        }
    }
}
