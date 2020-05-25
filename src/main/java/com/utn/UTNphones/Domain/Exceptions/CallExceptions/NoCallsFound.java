package com.utn.UTNphones.Domain.Exceptions.CallExceptions;

public class NoCallsFound extends CallException{
    public NoCallsFound() {
        super("No call found");
    }
}
