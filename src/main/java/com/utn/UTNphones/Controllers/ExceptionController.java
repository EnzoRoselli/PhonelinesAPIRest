package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class ExceptionController {
    public static void userRegisterException(SQLException ex) throws Exception {
        switch (ex.getErrorCode()) {
            case 1452:
                throw new CityDoesntExist();
            case 1062:
                throw new UserIdentificationAlreadyExists();
            case 1265:
                throw new UserTypeDoesntExist();
            default:
                throw new Exception("External error");
        }
    }


    public static void phonelineAddException(DataAccessException Error) throws Exception {
        JDBCException ex = (JDBCException) (Error).getCause();
        phonelineAddExceptionSQLCode(ex.getErrorCode());
    }

    private static void phonelineAddExceptionSQLCode(int errorNumber) throws Exception {
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

    public static void userUpdateException(Throwable Error) throws Exception {

        //City id
        if (Error.getMessage().contains("fk_users_city"))
            throw new CityDoesntExist();
            //Identification unique
        else if (Error.getMessage().contains("for key 'identification_card'"))
            throw new UserIdentificationAlreadyExists();
            //User type enum
        else if (Error.getMessage().contains("user_type"))
            throw new UserTypeDoesntExist();

        else throw new Exception("External error");
    }

    public static void phonelineUpdateException(Throwable Error) throws Exception {

        //City id
        if (Error.getMessage().contains("Domains.City"))
            throw new CityDoesntExist();
            //User id
        else if (Error.getMessage().contains("Domains.User"))
            throw new UserDoesntExist();
            //User type enum
        else if (Error.getMessage().contains("phoneline_type"))
            throw new PhonelineTypeError();

        else throw new Exception("External error");
    }
}
