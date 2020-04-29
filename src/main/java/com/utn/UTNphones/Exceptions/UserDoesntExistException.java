package com.utn.UTNphones.Exceptions;

public class UserDoesntExistException extends Exception{
    private String error;
    public UserDoesntExistException(){
        super();
        this.error="The users doesnt exist.";
    }
    @Override
    public String getMessage() {
        return error;
    }
}