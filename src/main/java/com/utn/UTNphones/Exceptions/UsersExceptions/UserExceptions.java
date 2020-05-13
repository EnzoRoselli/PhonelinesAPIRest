package com.utn.UTNphones.Exceptions.UsersExceptions;

public abstract class UserExceptions extends Exception {
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

    public UserExceptions(Throwable cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}