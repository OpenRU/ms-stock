package edu.fafic.msstock.application.error;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
