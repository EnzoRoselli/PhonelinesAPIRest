package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
public class UserController {

    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }


    public User login(User user) throws ParametersException, UserExceptions {
        if (user.getIdentification() == null || user.getPassword() == null) {
            throw new ParametersException("Parameters can´t contain null values");
        }
        return userService.login(user);
    }


    public User register(User user) throws Exception {
        if (user.hasNullAtribute()) {throw new ParametersException("Parameters can´t contain null values");}
        try {
            return userService.register(user);
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return user;
    }


    public void delete(String identification) throws ParametersException, UserExceptions {
        if (identification == null) {
            throw new ParametersException("Parameter id can´t contain null value");
        }
        this.userService.findByIdentification(identification);
        this.userService.deleteByIdentification(identification);
    }

    public User getById(Integer id) throws UserDoesntExist {
        return this.userService.getById(id);
    }


    public User update(User user) throws Exception {
        if (user.getId() == null) {
            throw new ParametersException("Parameter id can´t contain null value");
        }
        User inDataBaseUser = this.userService.findById(user.getId());
        user.setNonNullValues(inDataBaseUser);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }
        return user;
    }
}
