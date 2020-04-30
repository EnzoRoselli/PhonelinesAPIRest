package com.utn.UTNphones.Exceptions;

public class CityExceptions extends Exception{
    private String error;
    public CityExceptions(String message){
        super();
        this.error="The city id doesn`t exist.";
    }

    @Override
    public String getMessage() {
        return error;
    }
}