package com.utn.UTNphones.Services;

import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Services.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService implements ICallService {

    private final ICallRepository callRepository;

    @Autowired
    public CallService(ICallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public List<Call> getCallsByUserId(int userId) {
//        return callRepository.findAllBy();
        return null;
    }
}
