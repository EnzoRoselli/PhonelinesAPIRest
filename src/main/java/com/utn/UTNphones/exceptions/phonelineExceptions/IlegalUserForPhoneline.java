package com.utn.UTNphones.exceptions.phonelineExceptions;

public class IlegalUserForPhoneline extends PhonelineExceptions {

    public IlegalUserForPhoneline() {
        super("The user is not a client");
    }
}
