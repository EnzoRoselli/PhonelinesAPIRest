package com.utn.UTNphones.Domain.Exceptions.UsersExceptions;

public abstract class UserExceptions extends Exception {
    private String message;

    public UserExceptions(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}