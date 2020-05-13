package com.utn.UTNphones.Exceptions.CityExceptions;

public class CityDoesntExist extends CityExceptions {
    public CityDoesntExist(Throwable cause) {
        super("The city doesn´t exist", cause);
    }

    public CityDoesntExist() {
        super("The city doesn´t exist");
    }
}
