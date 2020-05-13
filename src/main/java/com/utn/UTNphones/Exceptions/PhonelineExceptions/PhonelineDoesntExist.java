package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public class PhonelineDoesntExist extends PhonelineExceptions{
    public PhonelineDoesntExist(Throwable cause) {
        super("The phonline doesn´t exist", cause);
    }

    public PhonelineDoesntExist() {
        super("The phonline doesn´t exist");
    }
}
