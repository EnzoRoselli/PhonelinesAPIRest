package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


public interface IUserService {
     User login(User user) throws UserExceptions;
     User register(User user) throws DataAccessException, UserExceptions;
     @Transactional
     void deleteByIdentification(String identification);

     User update(User user) throws UserExceptions;
     User findById(Integer id) throws UserExceptions;
     User findByIdentification(String identification) throws UserExceptions;

     User getById(Integer id) throws UserDoesntExist;
}
