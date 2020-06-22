package com.utn.UTNphones.Exceptions.RateExceptions;

public class RateNotExists extends RateException {
    public RateNotExists() {
        super("The rate doesn´t exist");
    }
}
