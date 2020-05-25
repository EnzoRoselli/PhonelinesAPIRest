package com.utn.UTNphones.Domain.Exceptions.CallExceptions;

public abstract class CallException extends Exception {
    private String message;

    public CallException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}