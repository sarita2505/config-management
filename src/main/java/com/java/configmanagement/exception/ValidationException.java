package com.java.configmanagement.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable e, String errorMessage , String... args){
        super(e);
    }
}
