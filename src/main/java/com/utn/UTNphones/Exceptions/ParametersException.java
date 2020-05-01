package com.utn.UTNphones.Exceptions;



public class ParametersException extends Exception{
private String error;
private Throwable cause;
    public ParametersException(Throwable cause){
        super();
        this.error="no parameter can be null.";
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
