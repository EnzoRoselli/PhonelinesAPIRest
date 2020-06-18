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

    public User login(User user) {
        user = userRepository.findByIdentificationAndPasswordAndType(user.getIdentification(), user.getPassword(), user.getType()).orElseThrow(LogException::new);
        if (user.getType().equals("employee") && !user.getStatus()) {
            throw new LogException();
        }
        return user;
    }

    public User register(User user) throws DataAccessException {
        return Optional.of(userRepository.save(user))
                .orElseThrow(UserDoesntExist::new);
    }

    public User update(User user) {
        return Optional.of(this.userRepository.save(user))
                .orElseThrow(UserDoesntExist::new);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserDoesntExist::new);
    }

    public User findByIdentification(String identification) {
        return this.userRepository.findByIdentification(identification)
                .orElseThrow(UserDoesntExist::new);
    }

    public void delete(Integer userId) {
        this.userRepository.deleteById(userId);
    }


}