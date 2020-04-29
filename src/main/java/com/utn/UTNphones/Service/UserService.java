package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IEmployeeRepository;
import com.utn.UTNphones.Repository.IUserRepository;
import com.utn.UTNphones.Service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
