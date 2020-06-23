package com.utn.UTNphones.exceptions.usersExceptions;

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