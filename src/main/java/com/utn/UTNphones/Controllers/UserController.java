package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Optional;

@Controller
public class UserController {

    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }


    public User login(LoginDTO loginDTO) throws LogException {
        User u = User.builder().identification(loginDTO.getIdentification()).password(loginDTO.getPassword()).build();
        return userService.login(u);
    }

    public User register(User user) throws Exception {
        if (user.hasNullAttribute()) {throw new ParametersException("Parameters can´t contain null values");}
        try {
            return userService.register(user);
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return user;
    }

    public void delete(String identification) throws UserDoesntExist {
        this.userService.findByIdentification(identification);
        this.userService.deleteByIdentification(identification);
    }

    public User findById(Integer id) throws UserDoesntExist {
        return this.userService.findById(id);
    }

    public User update(User user) throws Exception {
        User inDataBaseUser = this.userService.findById(user.getId());
        user=setNonNullValues(user,inDataBaseUser);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }
        return user;
    }

    private User setNonNullValues(User newUser,User oldUser) {
        Optional.ofNullable(newUser.getPassword()).ifPresent(oldUser::setPassword);
        Optional.ofNullable(newUser.getIdentification()).ifPresent(oldUser::setIdentification);
        Optional.ofNullable(newUser.getCity()).ifPresent(oldUser::setCity);
        Optional.ofNullable(newUser.getLastname()).ifPresent(oldUser::setLastname);
        Optional.ofNullable(newUser.getName()).ifPresent(oldUser::setName);
        Optional.ofNullable(newUser.getType()).ifPresent(oldUser::setType);
        return oldUser;
    }
}
