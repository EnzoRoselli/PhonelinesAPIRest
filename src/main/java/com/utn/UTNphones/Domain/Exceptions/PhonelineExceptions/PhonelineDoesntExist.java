package com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions;

public class PhonelineDoesntExist extends PhonelineExceptions{
    public PhonelineDoesntExist() {
        super("The phoneline doesn´t exist");
    }
}
