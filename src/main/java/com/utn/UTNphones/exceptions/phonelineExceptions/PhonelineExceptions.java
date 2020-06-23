package com.utn.UTNphones.exceptions.phonelineExceptions;

public abstract class PhonelineExceptions extends RuntimeException {
    private final String message;

    public PhonelineExceptions(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}