package com.utn.UTNphones.exceptions.phonelineExceptions;

public class PhonelineDigitsCountPlusPrefix extends PhonelineExceptions {
    public PhonelineDigitsCountPlusPrefix() {
        super("The prefix plus the numbers, are more or less than 10 digits");
    }
}
