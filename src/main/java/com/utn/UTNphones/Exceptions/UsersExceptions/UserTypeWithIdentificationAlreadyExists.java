package com.utn.UTNphones.Exceptions.UsersExceptions;

public class UserTypeWithIdentificationAlreadyExists extends UserExceptions {

    public UserTypeWithIdentificationAlreadyExists() {
        super("The identification card is already registered with an user type");
    }
}
