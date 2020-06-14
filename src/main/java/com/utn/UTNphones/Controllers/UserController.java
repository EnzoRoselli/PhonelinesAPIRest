package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.LoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.Dto.Requests.UserPatchUpdateDTO;
import com.utn.UTNphones.Domains.User;
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

    public User adminlogin(LoginDTO loginDTO) {
        return userService.adminLogin(User.fromLoginDto(loginDTO));
    }

    public User clientLogin(LoginDTO loginDTO) {
        return userService.clientLogin(User.fromLoginDto(loginDTO));
    }

    public User register(UserDTO userDTO) throws Exception {
        try {
            return userService.register(User.fromDto(userDTO));
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userRegisterException(SQLex);
        }
        return null;
    }

    public User findById(Integer id) {
        return this.userService.findById(id);
    }

    public User modification(Integer userId, UserPatchUpdateDTO newUser) throws Exception {
        User userRenovated = this.userService.findById(userId);
        setNonNullValues(newUser, userRenovated);
        userRenovated.setId(userId);
        try {
            return this.userService.update(userRenovated);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return userRenovated;
    }

    public User update(Integer userId, UserDTO userUpdate) throws Exception {
        this.userService.findById(userId);
        User user = User.fromDto(userUpdate);
        user.setId(userId);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return user;
    }

    public void delete(Integer userId) {
        this.findById(userId);
        this.userService.delete(userId);
    }

    private void setNonNullValues(UserPatchUpdateDTO newUser, User userUpdated) {
        Optional.ofNullable(newUser.getPassword()).ifPresent(userUpdated::setPassword);
        Optional.ofNullable(newUser.getIdentification()).ifPresent(userUpdated::setIdentification);
        Optional.ofNullable(newUser.getCityId()).ifPresent(value->userUpdated.setCity(City.builder().id(value).build()));
        Optional.ofNullable(newUser.getLastname()).ifPresent(userUpdated::setLastname);
        Optional.ofNullable(newUser.getName()).ifPresent(userUpdated::setName);
        Optional.ofNullable(newUser.getType()).ifPresent(userUpdated::setType);
    }


}
