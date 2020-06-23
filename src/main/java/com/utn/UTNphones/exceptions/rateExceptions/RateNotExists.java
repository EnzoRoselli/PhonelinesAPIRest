package com.utn.UTNphones.exceptions.rateExceptions;

public class RateNotExists extends RateException {
    public RateNotExists() {
        super("The rate doesn´t exist");
    }
}
