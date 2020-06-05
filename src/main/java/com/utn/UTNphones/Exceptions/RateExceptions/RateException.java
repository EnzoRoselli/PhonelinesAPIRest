package com.utn.UTNphones.Exceptions.RateExceptions;

public abstract class RateException extends RuntimeException {
    private final String message;

    public RateException(String message) {
        this.message = message;
    }
}
