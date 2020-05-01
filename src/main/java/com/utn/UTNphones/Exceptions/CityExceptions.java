package com.utn.UTNphones.Exceptions;

public class CityExceptions extends Exception{
    private Throwable cause;
    private String error;
    public CityExceptions(String message,Throwable cause){
        super();
        this.error="The city id doesn`t exist.";
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