package com.utn.UTNphones.Exceptions;

public class UserExceptions extends Exception {
    private  Throwable cause;
    private String error;

    public UserExceptions(String message,Throwable cause) {
        super();
        this.error = message;
        this.cause=cause;
    }

    @Override
    public String getMessage() {
        return error;
    }

    public Throwable getCause() {
        return cause;
    }
}