package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;

import java.util.List;

public interface ICallService {
    List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) throws CallException;
    List<Object> getTopMostCalledCities(Integer userId);
}
