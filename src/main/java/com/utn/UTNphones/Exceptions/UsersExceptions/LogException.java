package com.utn.UTNphones.Exceptions.UsersExceptions;

public class LogException extends UserExceptions {
    public LogException( Throwable cause) {
        super("The information introduced doesn´t match", cause);
    }

    public LogException() {
        super("The information introduced doesn´t match");
    }
}
