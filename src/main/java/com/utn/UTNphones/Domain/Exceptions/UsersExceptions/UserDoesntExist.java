package com.utn.UTNphones.Domain.Exceptions.UsersExceptions;

public class UserDoesntExist extends UserExceptions{

    public UserDoesntExist() {
        super("The user doesn´t exist");
    }
}
