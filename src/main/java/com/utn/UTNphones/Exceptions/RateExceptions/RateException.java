package com.utn.UTNphones.Exceptions.RateExceptions;

public abstract class RateException  extends Exception{
    private String message;
    public RateException(String message) {
        this.message = message;
    }
}
