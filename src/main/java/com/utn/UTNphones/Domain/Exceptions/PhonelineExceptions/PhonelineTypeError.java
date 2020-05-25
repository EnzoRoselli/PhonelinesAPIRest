package com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions;

public class PhonelineTypeError extends PhonelineExceptions{
    public PhonelineTypeError() {
        super("The phoneline type doesn´t exist");
    }
}
