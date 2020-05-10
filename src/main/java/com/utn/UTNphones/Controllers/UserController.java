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
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService, ICityService cityService) {
        this.userService = userService;
    }

    @PostMapping(value = "login/")
    public User login(@RequestBody @NotNull User user) throws ParametersException, UserExceptions {
        if (user.getIdentification() == null || user.getPassword() == null) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            return userService.login(user);
        }
    }


    @PostMapping("")
    public User register(@RequestBody @NotNull User user) throws Exception {
        if (user.hasNullAtribute()) throw new ParametersException("Parameters can´t contain null values");
        if (user.hasValueErrors())
            throw new ParametersException("Error on attributes, identification(only numbers) or name/last name(no numbers)");
        try {
            return userService.register(user);
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return user;
    }

    @DeleteMapping("")
    public void delete(@RequestBody @NotNull User user) throws ParametersException, UserExceptions {
        if (user.getId() == null) throw new ParametersException("Parameter id can´t contain null value");
        this.userService.findById(user.getId());
        this.userService.deleteById(user.getId());
    }

    @PutMapping(value = "update/")
    public void update(@RequestBody @NotNull User user) throws Exception {
        if (user.getId() == null) throw new ParametersException("Parameter id can´t contain null value");
        User inDataBaseUser = this.userService.findById(user.getId());
        user.setNonNullValues(inDataBaseUser);
        if (user.hasValueErrors()) throw new ParametersException("Error on attributes, identification(only numbers) or name/last name(no numbers)");
        try {
            this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }

    }


}
