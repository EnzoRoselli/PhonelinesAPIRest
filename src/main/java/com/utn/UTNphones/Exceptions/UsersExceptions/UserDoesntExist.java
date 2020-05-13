package com.utn.UTNphones.Exceptions.UsersExceptions;

public class UserDoesntExist extends UserExceptions{
    public UserDoesntExist( Throwable cause) {
        super("The user doesn´t exist", cause);
    }

    public UserDoesntExist() {
        super("The user doesn´t exist");
    }
}
