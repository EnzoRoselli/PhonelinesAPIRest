package com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions;

public class PhonelineAlreadyExists extends PhonelineExceptions{


    public PhonelineAlreadyExists() {
        super("The phoneline already exists");
    }
}
