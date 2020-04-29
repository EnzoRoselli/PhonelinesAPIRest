package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserDoesntExistException;
import com.utn.UTNphones.Exceptions.UserExistsException;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login/")
    public User login(@RequestBody @NotNull User user) throws ParametersException, NumberFormatException, UserDoesntExistException {
        if (user.getIdentification()==null || user.getPassword()==null){
            throw new ParametersException();
        }else{
            User u= userService.login(user);
        }
         return user;
    }

    @PostMapping("/register/")
    public User register(@RequestBody @NotNull User user) throws ParametersException, UserExistsException {
       if (user.hasNullAtribute()){
          throw new ParametersException();
       }else{
           try {
               userService.register(user);
           } catch (DataAccessException throwable) {
               throw new UserExistsException();
           }
       }
        return user;
    }




}
