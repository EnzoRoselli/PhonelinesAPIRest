package com.utn.UTNphones.exceptions.rateExceptions;

public abstract class RateException extends RuntimeException {
    private final String message;

    public RateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
