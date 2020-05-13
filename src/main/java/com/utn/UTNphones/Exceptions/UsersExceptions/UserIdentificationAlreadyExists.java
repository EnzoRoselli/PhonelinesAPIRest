package com.utn.UTNphones.Exceptions.UsersExceptions;

public class UserIdentificationAlreadyExists extends UserExceptions{

    public UserIdentificationAlreadyExists(Throwable cause) {
        super("The identification_card is already registered", cause);
    }

    public UserIdentificationAlreadyExists() {
        super("The identification_card is already registered");
    }
}
