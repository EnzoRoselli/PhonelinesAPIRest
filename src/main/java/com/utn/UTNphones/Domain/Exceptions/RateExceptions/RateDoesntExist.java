package com.utn.UTNphones.Domain.Exceptions.RateExceptions;

public class RateDoesntExist extends RateException{
    public RateDoesntExist() {
        super("The rate doesn´t exist");
    }
}
