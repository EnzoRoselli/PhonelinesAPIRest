package com.utn.UTNphones.Domain.Exceptions.CityExceptions;

public abstract class CityExceptions extends Exception{
    private String message;
    public CityExceptions(String message){
        super();
        this.message=message;

    }
    @Override
    public String getMessage() {
        return message;
    }

}