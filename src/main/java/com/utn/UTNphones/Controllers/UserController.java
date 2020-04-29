package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UserDoesntExist;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login/")
    public User login(@RequestBody @NotNull User user) throws ParametersException, NumberFormatException, UserDoesntExist {
        if (user.getIdentification()==null || user.getPassword()==null){
            throw new ParametersException();
        }else{
            User u= userService.login(user.getIdentification(),user.getPassword());
        }
         return user;
    }

    @PostMapping("/register/{name}/{lastname}/{identification}/{password}/{cityName}/{provinceName}")
    public User register(@PathVariable("name")String name,@PathVariable("lastname")String lastname,@PathVariable("identification")String identification
            ,@PathVariable("password")String password,@PathVariable("cityname")String cityname,@PathVariable("provinceName")String provinceName){
        Integer identificationCard=Integer.parseInt(identification);

        return null;
    }

    //public boolean NullParameter


}
