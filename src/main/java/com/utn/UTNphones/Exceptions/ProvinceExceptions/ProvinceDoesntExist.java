package com.utn.UTNphones.Exceptions.ProvinceExceptions;

public class ProvinceDoesntExist extends ProvinceException{
    public ProvinceDoesntExist() {
        super("The province doesn´t exist");
    }
}
