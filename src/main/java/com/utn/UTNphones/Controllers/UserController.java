package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.Dto.UserPatchUpdateDTO;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import com.utn.UTNphones.Domains.Dto.UserUpdateDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Objects;
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

    public User findById(Integer id) {
        return this.userService.findById(id);
    }

    public User modification(UserPatchUpdateDTO newUser) throws Exception {
        User userRenovated = this.userService.findById(newUser.getId());
        setNonNullValues(newUser, userRenovated);
        try {
            return this.userService.update(userRenovated);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return userRenovated;
    }

    public User update(UserUpdateDTO userUpdate) throws Exception {
        this.userService.findById(userUpdate.getId());
        User user = new User(userUpdate);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return user;
    }

    private void setNonNullValues(UserPatchUpdateDTO newUser, User userUpdated) {
        Optional.ofNullable(newUser.getPassword()).ifPresent(userUpdated::setPassword);
        Optional.ofNullable(newUser.getIdentification()).ifPresent(userUpdated::setIdentification);
        if (newUser.getCityId()!=null){userUpdated.setCity(City.builder().id(newUser.getCityId()).build());}
        Optional.ofNullable(newUser.getLastname()).ifPresent(userUpdated::setLastname);
        Optional.ofNullable(newUser.getName()).ifPresent(userUpdated::setName);
        Optional.ofNullable(newUser.getType()).ifPresent(userUpdated::setType);
    }

    public void delete(Integer userId) {
        this.findById(userId);
        this.userService.delete(userId);
    }
}
