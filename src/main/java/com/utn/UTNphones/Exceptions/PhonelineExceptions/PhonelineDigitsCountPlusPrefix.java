package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public class PhonelineDigitsCountPlusPrefix extends PhonelineExceptions{
    public PhonelineDigitsCountPlusPrefix( Throwable cause) {
        super("The prefix plus the numbers, are more or less than 10 digits", cause);
    }

    public PhonelineDigitsCountPlusPrefix() {
        super("The prefix plus the numbers, are more or less than 10 digits");
    }
}
