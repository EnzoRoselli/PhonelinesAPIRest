package com.utn.UTNphones.Exceptions;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.UserExceptions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

public class ExceptionController {
    public static void userRegisterException(ConstraintViolationException ex) throws Exception {
        switch (ex.getSQLException().getErrorCode()) {
            case 1452:
                throw new CityExceptions("The city doesn´t exist",ex.getCause());
            case 1062:
                throw new UserExceptions("The user already exists",ex.getCause());
            case 1265:
                throw new UserExceptions("The client type doesn´t exist",ex.getCause());
            default:
                throw new Exception("External error");
        }
    }

    public static void phonelineAddException(DataAccessException classWithError) throws Exception {
        if (classWithError.getMessage().contains("Models.User")) throw new UserExceptions("The user doesn´t exist",classWithError.getCause());
        else if (classWithError.getMessage().contains("Models.City")) throw new CityExceptions("The city doesn´t exist",classWithError.getCause());
        else throw new Exception("External error");
    }
}
