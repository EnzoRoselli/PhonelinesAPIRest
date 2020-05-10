package com.utn.UTNphones.Exceptions;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.UserExceptions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ExceptionController {
    public static void userRegisterException(SQLException ex) throws Exception {
        switch (ex.getErrorCode()) {
            case 1452:
                throw new CityExceptions("The city doesn´t exist", ex.getCause());
            case 1062:
                throw new UserExceptions("The user's identification is already registered", ex.getCause());
            case 1265:
                throw new UserExceptions("The client type doesn´t exist", ex.getCause());
            default:
                throw new Exception("External error");
        }
    }


    public static void phonelineAddException(DataAccessException Error) throws Exception {
        ConstraintViolationException cve = (ConstraintViolationException) Error.getCause();
        switch (cve.getErrorCode()) {
            case 1452:
                throw new UserExceptions("The user doesn´t exist", cve.getCause());
            default:
                throw new Exception("External error");
        }
    }

    public static void userUpdateException(DataAccessException Error) throws Exception {

        //City id
        if (Error.getRootCause().getMessage().contains("Models.City")) //110
            throw new CityExceptions("The city doesn´t exist", Error.getCause());
            //Identification unique
        else if (Error.getRootCause().getMessage().contains("for key 'identification_card'")) //92
            throw new UserExceptions("The identification_card is already registered", Error.getCause());
            //User type enum
        else if (Error.getRootCause().getMessage().contains("type_user"))//91  - 98 root
            throw new ParametersException("The user`s type doesn´t exist");

        else throw new Exception("External error");
    }


}
