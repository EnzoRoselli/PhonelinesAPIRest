package com.utn.UTNphones.Domain.Exceptions.UsersExceptions;

public class UserTypeDoesntExist extends UserExceptions{

    public UserTypeDoesntExist() {
        super("The user`s type doesn´t exist");
    }
}
