package com.utn.UTNphones.controllers;

import com.utn.UTNphones.exceptions.cityExceptions.CityNotExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.exceptions.usersExceptions.UserNotExists;
import com.utn.UTNphones.exceptions.usersExceptions.UserTypeNotExists;
import com.utn.UTNphones.exceptions.usersExceptions.UserTypeWithIdentificationAlreadyExists;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class ExceptionController {
    public static void userExceptionSQLCode(Integer errorCode) throws Exception {
        switch (errorCode) {
            case 1452:
                throw new CityNotExists();
            case 1062:
                throw new UserTypeWithIdentificationAlreadyExists();
            case 1265:
                throw new UserTypeNotExists();
            default:
                throw new Exception("External error");
        }
    }

    public static void userUpdateException(Throwable error) throws Exception {
        if (error instanceof JDBCException) {
            userExceptionSQLCode(((JDBCException) error).getErrorCode());
        } else {
            if (error.getMessage().contains("domains.City"))
                throw new CityNotExists();

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
                throw new UserNotExists();
            case 1265:
                throw new PhonelineTypeError();
            case 1062:
                throw new PhonelineAlreadyExists();
            default:
                throw new Exception("External error");
        }
    }


    public static void phonelineUpdateException(Throwable error) throws Exception {
        if (error instanceof SQLException) {
            phonelineExceptionSQLCode(((SQLException) error).getErrorCode());
        } else {
            //City id
            if (error.getMessage().contains("domains.City"))
                throw new CityNotExists();
                //User id
            else if (error.getMessage().contains("domains.User"))
                throw new UserNotExists();

            else throw new Exception("External error");
        }
    }
}
