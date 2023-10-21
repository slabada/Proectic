package org.handler;

// Этот класс определяет пользовательские исключения, которые могут возникнуть в микросервисе.
public class CustomExceptions {

    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException() {super("Предоставлен неверный идентификатор.");}
    }

    public static class PositionNotFoundException extends RuntimeException {
        public PositionNotFoundException() {
            super("Должность не найдена.");
        }
    }

    public static class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException() {
            super("Работник не найден.");
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

    public static class TaxBenefitNotFoundException extends RuntimeException {
        public TaxBenefitNotFoundException() {
            super("льгота не найдена.");
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
            super("зарплатная карточка не найдена.");
        }
    }
}
