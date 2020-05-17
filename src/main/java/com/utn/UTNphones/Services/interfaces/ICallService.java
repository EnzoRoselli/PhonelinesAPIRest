package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Models.Phoneline;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ICallService {
    void add(Call call) throws CallException;
    List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException;
    List<Object> getTopMostCalledCities(Integer userId) throws CallException;

    List<Call> getBetweenDates(SearchBetweenDates dates);
}
