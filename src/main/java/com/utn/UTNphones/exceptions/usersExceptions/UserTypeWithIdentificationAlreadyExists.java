package com.utn.UTNphones.exceptions.usersExceptions;

public class UserTypeWithIdentificationAlreadyExists extends UserExceptions {

    public UserTypeWithIdentificationAlreadyExists() {
        super("The identification card is already registered with the user type");
    }
}
