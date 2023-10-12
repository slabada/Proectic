package com.financemicroservice.Handler;

// Этот класс определяет пользовательские исключения, которые могут возникнуть в микросервисе.

public class CustomExceptions {
    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException() {
            super("Предоставлен неверный идентификатор.");
        }
    }

    public static class TaxBenefitNotFoundException extends RuntimeException {
        public TaxBenefitNotFoundException() {
            super("льгта не найдена.");
        }
    }

    public static class TaxBenefitAlreadyExistsException extends RuntimeException{
        public TaxBenefitAlreadyExistsException(){
            super("Такая льгота уже существует.");
        }
    }

    public static class TaxRateNotFoundException extends RuntimeException {
        public TaxRateNotFoundException() {
            super("налог не найдена.");
        }
    }

    public static class TaxRateAlreadyExistsException extends RuntimeException{
        public TaxRateAlreadyExistsException(){
            super("Такой налог уже существует.");
        }
    }

    public static class NegativeSalaryException extends RuntimeException{
        public NegativeSalaryException(){
            super("Зарплата должна быть больше 0.");
        }
    }

    public static class NegativePayDayException extends RuntimeException{
        public NegativePayDayException(){
            super("Дата зарплаты не может быть в прошлом или пустой.");
        }
    }

    public static class PayRollCardFoundException extends RuntimeException {
        public PayRollCardFoundException() {
            super("зарлатная карточка не найдена.");
        }
    }

    public static class EmployeeFoundException extends RuntimeException {
        public EmployeeFoundException() {
            super("работник не найден.");
        }
    }
}
