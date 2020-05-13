package com.utn.UTNphones.Exceptions;

public class ParametersException extends Exception{
private String message;
private Throwable cause;
    public ParametersException(String messageException,Throwable cause){
        super();
        this.message=messageException;
        this.cause=cause;
    }

    public ParametersException(String messageException) {
        super();
        this.message=messageException;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public Throwable getCause() {
        return cause;
    }
}
