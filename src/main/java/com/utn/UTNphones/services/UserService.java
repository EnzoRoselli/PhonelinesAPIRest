package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.exceptions.usersExceptions.LogException;
import com.utn.UTNphones.exceptions.usersExceptions.UserNotExists;
import com.utn.UTNphones.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public User login(User user) {
        user = userRepository.findByIdentificationAndPasswordAndType(user.getIdentification(), user.getPassword(), user.getType()).orElseThrow(LogException::new);
        if (user.getType().equals("employee") && !user.getStatus()) {
            throw new LogException();
        }
        return user;
    }

    public User register(User user) throws DataAccessException {
        return Optional.of(userRepository.save(user))
                .orElse(user);
    }

    public User update(User user) {
        return Optional.of(this.userRepository.save(user))
                .orElse(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotExists::new);
    }

    public void delete(Integer userId) {
        this.userRepository.deleteById(userId);
    }


}