package com.utn.UTNphones.Exceptions;

public class UserExceptions extends Exception {
    private String error;

    public UserExceptions(String message) {
        super();
        this.error = message;
    }

    @Override
    public String getMessage() {
        return error;
    }
}