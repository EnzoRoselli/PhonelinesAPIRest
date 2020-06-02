package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.CallsWithNameAndLastname;
import com.utn.UTNphones.Domains.Dto.CityTop;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Services.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CallService implements ICallService {

    private final ICallRepository callRepository;

    @Autowired
    public CallService(ICallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public void add(Call call){
            callRepository.save(call);
    }

    @Override
    public List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws NoCallsFound {
       List<Call> calls = callRepository.findByOriginPhonelineIn(phoneListOfTheUser);
       if (calls.isEmpty()){throw new NoCallsFound();}
       return calls;
    }

    @Override
    public List<CityTop> getTopMostCalledCities(Integer userId) throws NoCallsFound {
        List<CityTop> cityPlusCounter=this.callRepository.findTopMostCalledCities(userId);
        if (cityPlusCounter.isEmpty()){throw new NoCallsFound();}
        return cityPlusCounter;
    }

    @Override
    public List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDatesDTO dates) {
        return this.callRepository.findAllByOriginPhonelineUserIdAndDateBetween(userId,dates.getStart(),dates.getEnd());
    }

    @Override
    public List<Call> getByUserEndDate(Integer userId, Date endDate) {
        return this.callRepository.findAllByOriginPhonelineUserIdAndDateBefore(userId, endDate);
    }

    @Override
    public List<Call> getByUserStartDate(Integer userId, Date startDate) {
        return this.callRepository.findAllByOriginPhonelineUserIdAndDateAfter(userId, startDate);
    }

    @Override
    public List<CallsWithNameAndLastname> getByDate(LocalDate date) {
        return this.callRepository.findAllByDate(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
    }

}
