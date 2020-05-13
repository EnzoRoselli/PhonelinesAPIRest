package com.utn.UTNphones.Exceptions.UsersExceptions;

public class UserTypeDoesntExist extends UserExceptions{
    public UserTypeDoesntExist(Throwable cause) {
        super("The user`s type doesn´t exist", cause);
    }

    public UserTypeDoesntExist() {
        super("The user`s type doesn´t exist");
    }
}
