package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public class PhonelineTypeError extends PhonelineExceptions{
    public PhonelineTypeError( Throwable cause) {
        super("The phoneline type doesn´t exist", cause);
    }

    public PhonelineTypeError() {
        super("The phoneline type doesn´t exist");
    }
}
