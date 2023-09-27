package com.employeemanagement.employeemanagementmicroservice.Handler;

public class CustomExceptions {

    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException() {
            super("Предоставлен неверный идентификатор.");
        }
    }

    public static class PositionNotFoundException extends RuntimeException {
        public PositionNotFoundException() {
            super("Должность не найдена.");
        }
    }

    public static class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException() {
            super("Работник не найдена.");
        }
    }

    public static class PositionAlreadyExistsException extends RuntimeException{
        public PositionAlreadyExistsException(){
            super("Такая должность уже существует.");
        }
    }

    public static class InvalidPageSizeException extends RuntimeException{
        public InvalidPageSizeException(){
            super("Недопустимые параметры страницы.");
        }
    }
}
