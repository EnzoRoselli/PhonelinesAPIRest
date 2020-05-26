package com.utn.UTNphones.Domain.Exceptions.PhonelineExceptions;

public class PhonelinesNotRegisteredByUser extends PhonelineExceptions{
    public PhonelinesNotRegisteredByUser() {
        super("No phones registered with this user");
    }
}
