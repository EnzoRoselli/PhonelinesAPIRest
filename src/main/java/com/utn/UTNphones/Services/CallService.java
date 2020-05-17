package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Services.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
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
    public void add(Call call) throws CallException {
        try {
            callRepository.save(call);
        }catch (DataAccessException ex){
            throw new CallException(ex.getCause().getCause().getMessage(),ex.getCause());
        }
    }

    @Override
    public List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException {
       List<Call> calls = callRepository.findByOriginPhonelineIn(phoneListOfTheUser);
        return Optional.ofNullable(calls).orElseThrow(()->new CallException("No calls found"));
    }

    @Override
    public List<Object> getTopMostCalledCities(Integer userId) throws CallException {
        List<Object> cityPlusCounter=this.callRepository.findTopMostCalledCities(userId);
        return Optional.ofNullable(cityPlusCounter).orElseThrow(()->new CallException("No calls found"));
    }

    @Override
    public List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDates dates) {
        return this.callRepository.findAllByUserIdAndDateBetween(userId,dates.getStart(),dates.getEnd());
    }

}
