package com.utn.UTNphones.Exceptions.CallExceptions;

public class CallException extends Exception {
    private Throwable cause;
    private String message;

    public CallException(String message, Throwable cause) {
        super();
        this.message = message;
        this.cause = cause;
    }

    public CallException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}