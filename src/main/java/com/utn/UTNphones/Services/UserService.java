package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
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
    public User login(User user) throws LogException {

        return Optional.ofNullable(userRepository.findByIdentificationAndPassword
                (user.getIdentification(), user.getPassword())).orElseThrow(LogException::new);
    }

    @Override
    public User register(User user) throws DataAccessException, UserDoesntExist {

        return Optional.ofNullable(userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    @Override
    public void deleteByIdentification(String identification) {
        this.userRepository.deleteByIdentification(identification);
    }

    public User update(User user) throws UserDoesntExist {
        return Optional.ofNullable(this.userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    @Override
    public User findById(Integer id) throws UserDoesntExist {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserDoesntExist();
         return user.get();
    }

    @Override
    public User findByIdentification(String identification) throws UserDoesntExist {

        return Optional.ofNullable(this.userRepository.findByIdentification(identification)).orElseThrow(UserDoesntExist::new);
    }


}