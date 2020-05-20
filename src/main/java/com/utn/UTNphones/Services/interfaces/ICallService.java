package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Dto.CityTopDto;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Models.Phoneline;

import java.util.List;

public interface ICallService {
    void add(Call call) throws CallException;
    List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException;
    List<CityTopDto> getTopMostCalledCities(Integer userId) throws CallException;

    List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDates dates);
}
