package com.utn.UTNphones.Domain.Exceptions.UsersExceptions;

public class UserIdentificationAlreadyExists extends UserExceptions{

    public UserIdentificationAlreadyExists() {
        super("The identification card is already registered");
    }
}
