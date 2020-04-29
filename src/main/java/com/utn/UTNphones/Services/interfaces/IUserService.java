package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.UserDoesntExistException;
import com.utn.UTNphones.Models.User;
import org.springframework.dao.DataAccessException;


public interface IUserService {
    public User login(User user) throws UserDoesntExistException;
    public User register(User user)throws DataAccessException;
}
