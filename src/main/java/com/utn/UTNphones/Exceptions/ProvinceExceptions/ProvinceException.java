package com.utn.UTNphones.Exceptions.ProvinceExceptions;

public abstract class ProvinceException extends Exception{
    private String message;

    public ProvinceException(String message) {
        this.message = message;
    }
}
