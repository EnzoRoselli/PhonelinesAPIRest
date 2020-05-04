package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import org.springframework.dao.DataAccessException;


public interface IUserService {
     User login(User user) throws UserExceptions;
     User register(User user)throws DataAccessException;
     void deleteById(Integer id);
     User update(User user) throws UserExceptions;
     User findById(Integer id) throws UserExceptions;
}
