package com.utn.UTNphones.Exceptions;

public class ParametersException extends Exception{
private String message;

    public ParametersException(String messageException) {
        super();
        this.message=messageException;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
