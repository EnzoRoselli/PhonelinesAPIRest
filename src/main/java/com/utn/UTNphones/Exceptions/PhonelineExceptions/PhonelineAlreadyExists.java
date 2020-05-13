package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public class PhonelineAlreadyExists extends PhonelineExceptions{
    public PhonelineAlreadyExists(Throwable cause) {
        super("The phoneline already exists", cause);
    }

    public PhonelineAlreadyExists() {
        super("The phoneline already exists");
    }
}
