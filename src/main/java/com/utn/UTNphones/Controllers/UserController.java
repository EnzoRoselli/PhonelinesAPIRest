package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
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

    @PostMapping("register/")
    public User register(@RequestBody @NotNull User user) throws Exception {
        if (user.hasNullAtribute()) {
            throw new ParametersException("Parameters can´t contain null values");
        } else {
            try {
               return userService.register(user);
            } catch (DataAccessException ex) {
                ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
                ExceptionController.userRegisterException(cve);
            }
            return user;
        }
    }


}
