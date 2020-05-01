package com.utn.UTNphones.Exceptions;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.UserExceptions;

public class ExceptionController {
    public static void userRegisterException(Integer errorCode) throws Exception {
        switch (errorCode) {
            case 1452:
                throw new CityExceptions("The city doesn´t exist");
            case 1062:
                throw new UserExceptions("The user already exists");
            default:
                throw new Exception("External error");
        }
    }

    public static void phonelineAddException(String classWithError) throws Exception {
        if (classWithError.contains("Models.User")) throw new UserExceptions("The user doesn´t exist");
        else if (classWithError.contains("Models.City")) throw new CityExceptions("The city doesn´t exist");
        else throw new Exception("External error");
    }
}
