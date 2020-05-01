package com.utn.UTNphones.Exceptions;

public class UserExceptions extends Exception {
    private  Throwable cause=new Throwable();
    private String message;

    public UserExceptions(String message,Throwable cause) {
        super();
        this.message = message;
        this.cause=cause;
    }
    public UserExceptions(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}