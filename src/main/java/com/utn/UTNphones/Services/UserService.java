package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public User login(User user) throws LogException {
        return Optional.ofNullable(userRepository.findByIdentificationAndPassword
                (user.getIdentification(), user.getPassword())).orElseThrow(LogException::new);
    }

    public User register(User user) throws DataAccessException, UserDoesntExist {
        return Optional.of(userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    public void deleteByIdentification(String identification) {
        this.userRepository.deleteByIdentification(identification);
    }

    public User update(User user) throws UserDoesntExist {
        return Optional.of(this.userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    public User findById(Integer id) throws UserDoesntExist {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserDoesntExist();
        return user.get();
    }

    public User findByIdentification(String identification) throws UserDoesntExist {
        return Optional.ofNullable(this.userRepository.findByIdentification(identification)).orElseThrow(UserDoesntExist::new);
    }


}