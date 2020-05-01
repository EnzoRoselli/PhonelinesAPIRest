package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Repositories.IUserRepository;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(User user) throws UserExceptions {
        User u = userRepository.findByIdentificationAndPassword(user.getIdentification(), user.getPassword());
        return Optional.ofNullable(u).orElseThrow(() -> new UserExceptions("The user doesn`t exist"));
    }
    @Override
    public User register(User user) throws DataAccessException {
        return userRepository.save(user);
    }
}