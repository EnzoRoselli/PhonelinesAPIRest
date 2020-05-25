package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domain.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Domain.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Domain.Call;
import com.utn.UTNphones.Domain.Dto.CityTop;
import com.utn.UTNphones.Domain.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domain.Phoneline;

import java.util.List;

public interface ICallService {
    void add(Call call) throws CallException;
    List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException;
    List<CityTop> getTopMostCalledCities(Integer userId) throws NoCallsFound;

    List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDates dates);
}
