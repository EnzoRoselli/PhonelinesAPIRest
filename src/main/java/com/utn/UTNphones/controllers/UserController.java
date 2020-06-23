package com.utn.UTNphones.controllers;

import com.utn.UTNphones.domains.dto.requests.Login;
import com.utn.UTNphones.domains.dto.requests.UserDTO;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public User login(Login loginDTO) {
        return userService.login(User.fromLoginDto(loginDTO));
    }

    public User register(UserDTO userDTO) throws Exception {
        try {
            return userService.register(User.fromDto(userDTO));
        } catch (DataAccessException ex) {
            SQLException SQLex = (SQLException) ex.getCause().getCause();
            ExceptionController.userExceptionSQLCode(SQLex.getErrorCode());
        }
        return null;
    }

    public User findById(Integer id) {
        return this.userService.findById(id);
    }

    public User update(Integer userId, UserDTO userUpdate) throws Exception {
        this.userService.findById(userId);
        User user = User.fromDto(userUpdate);
        user.setId(userId);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(Objects.requireNonNull(ex.getCause()));
        }
        return user;
    }

    public User delete(Integer userId) {
        User user = this.findById(userId);
        this.userService.delete(userId);
        return user;
    }
}
