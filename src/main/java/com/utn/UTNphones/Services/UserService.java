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

    public User clientLogin(User user) {
        return Optional.ofNullable(userRepository.findByIdentificationAndPasswordAndType
                (user.getIdentification(), user.getPassword(), "client")).orElseThrow(LogException::new);
    }

    public User adminLogin(User user) {
        return Optional.ofNullable(userRepository.findByIdentificationAndPasswordAndTypeAndStatus
                (user.getIdentification(), user.getPassword(), "employee", true)).orElseThrow(LogException::new);
    }

    public User infrastructureLogin(User user) {
        return Optional.ofNullable(userRepository.findByIdentificationAndPasswordAndType
                (user.getIdentification(), user.getPassword(), "infrastructure")).orElseThrow(LogException::new);
    }

    public User register(User user) throws DataAccessException {
        return Optional.of(userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    public User update(User user) {
        return Optional.of(this.userRepository.save(user)).orElseThrow(UserDoesntExist::new);
    }

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserDoesntExist();
        return user.get();
    }

    public User findByIdentification(String identification) {
        return Optional.ofNullable(this.userRepository.findByIdentification(identification)).orElseThrow(UserDoesntExist::new);
    }


    public void delete(Integer userId) {
        this.userRepository.deleteById(userId);
    }


}