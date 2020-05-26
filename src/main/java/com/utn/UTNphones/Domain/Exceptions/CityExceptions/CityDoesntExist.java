package com.utn.UTNphones.Domain.Exceptions.CityExceptions;

public class CityDoesntExist extends CityExceptions {
    public CityDoesntExist() {
        super("The city doesn´t exist");
    }
}
