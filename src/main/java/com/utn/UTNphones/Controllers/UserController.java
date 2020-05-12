package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Controller
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService, ICityService cityService) {
        this.userService = userService;
    }

    public User login(User user) throws ParametersException, UserExceptions {
        if (user.getIdentification() == null || user.getPassword() == null) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            return userService.login(user);
        }
    }


    public User register(User user) throws Exception {
        if (user.hasNullAtribute()) {
            throw new ParametersException("Parameters can´t contain null values");
        }
        try {
            return userService.register(user);
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return user;
    }


    public void delete(Integer identification) throws ParametersException, UserExceptions {
        if (identification == null) {
            throw new ParametersException("Parameter id can´t contain null value");
        }
        this.userService.findById(identification);
        this.userService.deleteById(identification);
    }


    public void update(User user) throws Exception {
        if (user.getId() == null) {
            throw new ParametersException("Parameter id can´t contain null value");
        }
        User inDataBaseUser = this.userService.findById(user.getId());
        user.setNonNullValues(inDataBaseUser);
        try {
            this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }

    }


}
