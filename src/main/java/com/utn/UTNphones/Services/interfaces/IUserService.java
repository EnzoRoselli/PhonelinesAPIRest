package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.UserDoesntExist;
import com.utn.UTNphones.Models.User;

public interface IUserService {
    public User login(Integer ic,String password) throws UserDoesntExist;
}
