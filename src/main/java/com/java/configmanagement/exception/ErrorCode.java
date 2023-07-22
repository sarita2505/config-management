package com.java.configmanagement.exception;

public enum ErrorCode {
    INVALID_INPUT("Invalid input provided"),
    RESOURCE_NOT_FOUND("Requested resource not found"),
    INTERNAL_SERVER_ERROR("Internal server error occurred");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
