package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.ProvinceExceptions.ProvinceDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Dto.CityTop;
import com.utn.UTNphones.Models.Dto.CityTopDto;
import com.utn.UTNphones.Models.Dto.NewCallDto;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Services.interfaces.ICallService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import com.utn.UTNphones.Services.interfaces.IProvineService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CallController {

    private final ICallService callService;
    private final IUserService userService;
    private final IProvineService provinceService;
    private final IPhonelineService phonelineService;


    @Autowired
    public CallController(ICallService callService, IUserService userService, IPhonelineService phonelineService, IProvineService provineService, IProvineService provinceService) {
        this.callService = callService;
        this.userService = userService;
        this.phonelineService = phonelineService;
        this.provinceService = provinceService;
    }
    public List<Call> getCallsByUserId(Integer userId) throws UserExceptions, CallException, PhonelineExceptions, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        return callService.getCallsByPhoneNumbers(phoneLines);
    }

    public List<CityTop> getTopDestinationsCalled(Integer userId) throws  CallException {
        List<CityTop> listWithoutFormat = this.callService.getTopMostCalledCities(userId);

        return listWithoutFormat;
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDates dates) throws UserExceptions, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
        userService.findById(userId);
        return this.callService.getByUserAndBetweenDates(userId,dates);
    }

    public void registerCall(NewCallDto newCall) throws CallException {
        Call call=Call.builder().originPhone(newCall.getOriginNumber()).destinationPhone(newCall.getDestinationNumber()).duration(newCall.getDuration()).date(newCall.getDate()).build();
        this.callService.add(call);
    }


}
