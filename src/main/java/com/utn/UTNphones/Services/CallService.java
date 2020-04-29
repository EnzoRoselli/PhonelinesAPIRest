package com.utn.UTNphones.Services;

import com.utn.UTNphones.Repositories.IUserRepository;
import com.utn.UTNphones.Services.interfaces.ICallService;
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
