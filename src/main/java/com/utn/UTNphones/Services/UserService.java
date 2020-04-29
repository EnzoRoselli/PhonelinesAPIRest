package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.UserDoesntExist;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Repositories.IUserRepository;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(Integer ic,String password) throws UserDoesntExist {

        User u=userRepository.findByIdentificationAndPassword(ic,password);

        return Optional.ofNullable(u).orElseThrow(()->new UserDoesntExist());
    }


}
