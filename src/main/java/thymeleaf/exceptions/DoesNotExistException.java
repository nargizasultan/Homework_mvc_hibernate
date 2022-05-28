package thymeleaf.exceptions;

public class DoesNotExistException extends RuntimeException{
    public DoesNotExistException() {
    }

    public DoesNotExistException(String message) {
        super(message);
    }
}
