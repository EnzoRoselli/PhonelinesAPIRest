package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public class PhonelinesNotRegisteredByUser extends PhonelineExceptions{
    public PhonelinesNotRegisteredByUser(Throwable cause) {
        super("No phones registered with this user", cause);
    }

    public PhonelinesNotRegisteredByUser() {
        super("No phones registered with this user");
    }
}
