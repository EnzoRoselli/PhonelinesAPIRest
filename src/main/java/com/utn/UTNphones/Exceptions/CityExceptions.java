package com.utn.UTNphones.Exceptions;

public class CityExceptions extends Exception{
    private Throwable cause;
    private String message;
    public CityExceptions(String message,Throwable cause){
        super();
        this.message=message;
        this.cause=cause;
    }

    public CityExceptions(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public Throwable getCause() {
        return cause;
    }
}