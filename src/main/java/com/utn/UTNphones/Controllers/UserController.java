package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public User login(LoginDTO loginDTO) throws LogException {
        User u = User.builder().identification(loginDTO.getIdentification()).password(loginDTO.getPassword()).build();
        return userService.login(u);
    }

    public User register(UserRegisterDTO userRegisterDTO) throws Exception {
        try {
            User user = new User(userRegisterDTO);
            return userService.register(user);
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return null;
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
            ExceptionController.userUpdateException(ex.getRootCause());
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
