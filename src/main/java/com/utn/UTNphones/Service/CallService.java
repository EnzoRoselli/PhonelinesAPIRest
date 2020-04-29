package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IUserRepository;
import com.utn.UTNphones.Service.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService implements ICallService {

    private final IUserRepository userRepository;

    @Autowired
    public CallService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
