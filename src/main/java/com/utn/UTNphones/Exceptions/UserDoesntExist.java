package com.utn.UTNphones.Exceptions;

public class UserDoesntExist extends Exception{
    private String error;
    public UserDoesntExist(){
        super();
        this.error="The users doesnt exist.";
    }
    @Override
    public String getMessage() {
        return error;
    }
}