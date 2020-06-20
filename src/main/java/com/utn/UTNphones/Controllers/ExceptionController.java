package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeWithIdentificationAlreadyExists;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

public class ExceptionController {
    public static void userExceptionSQLCode(Integer errorCode) throws Exception {
        switch (errorCode) {
            case 1452:
                throw new CityDoesntExist();
            case 1062:
                throw new UserTypeWithIdentificationAlreadyExists();
            case 1265:
                throw new UserTypeDoesntExist();
            default:
                throw new Exception("External error");
        }
    }

    public static void userUpdateException(Throwable error) throws Exception {
        if (error instanceof JDBCException) {
            userExceptionSQLCode(((JDBCException) error).getErrorCode());
        } else {
            if (error.getMessage().contains("Domains.City"))
                throw new CityDoesntExist();

            else throw new Exception("External error");
        }
    }

    public static void phonelineAddException(DataAccessException error) throws Exception {
        JDBCException ex = (JDBCException) (error).getCause();
        phonelineExceptionSQLCode(ex.getErrorCode());
    }

    private static void phonelineExceptionSQLCode(int errorNumber) throws Exception {
        switch (errorNumber) {
            case 1452:
                throw new UserDoesntExist();
            case 1265:
                throw new PhonelineTypeError();
            case 1062:
                throw new PhonelineAlreadyExists();
            default:
                throw new Exception("External error");
        }
    }


    public static void phonelineUpdateException(Throwable error) throws Exception {
        if (error instanceof JDBCException) {
            userExceptionSQLCode(((JDBCException) error).getErrorCode());
        } else {
        //City id
        if (error.getMessage().contains("Domains.City"))
            throw new CityDoesntExist();
            //User id
        else if (error.getMessage().contains("Domains.User"))
            throw new UserDoesntExist();

        else throw new Exception("External error");
    }
    }
}
