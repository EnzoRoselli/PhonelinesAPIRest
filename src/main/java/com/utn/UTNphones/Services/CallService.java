package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Services.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService implements ICallService {

    private final ICallRepository callRepository;

    @Autowired
    public CallService(ICallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException {
       List<Call> calls = callRepository.findByOriginPhonelineIn(phoneListOfTheUser);
        return Optional.ofNullable(calls).orElseThrow(()->new CallException("No calls found"));
    }
}
