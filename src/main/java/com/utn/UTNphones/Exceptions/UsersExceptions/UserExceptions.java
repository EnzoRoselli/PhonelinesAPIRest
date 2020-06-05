package com.utn.UTNphones.Exceptions.UsersExceptions;

public abstract class UserExceptions extends RuntimeException {
    private final String message;

    public UserExceptions(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}