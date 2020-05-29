package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.CityTop;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;

import java.util.List;

public interface ICallService {
    void add(Call call) throws CallException;
    List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException;
    List<CityTop> getTopMostCalledCities(Integer userId) throws NoCallsFound;

    List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDates dates);
}
