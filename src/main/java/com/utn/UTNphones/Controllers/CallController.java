package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.CityTop;
import com.utn.UTNphones.Domains.Dto.NewCallDto;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Services.interfaces.ICallService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CallController {

    private final ICallService callService;
    private final IUserService userService;
    private final IPhonelineService phonelineService;


    @Autowired
    public CallController(ICallService callService, IUserService userService, IPhonelineService phonelineService) {
        this.callService = callService;
        this.userService = userService;
        this.phonelineService = phonelineService;
    }

    public List<Call> getCallsByUserId( Integer userId) throws UserDoesntExist, NoCallsFound, PhonelinesNotRegisteredByUser {
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        return callService.getCallsByPhoneNumbers(phoneLines);
    }

    public List<CityTop> getTopDestinationsCalled(Integer userId) throws NoCallsFound {
        return this.callService.getTopMostCalledCities(userId);
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDates dates) {
        return this.callService.getByUserAndBetweenDates(userId,dates);
    }

    public void registerCall(NewCallDto newCall) throws CallException {
        Call call=Call.builder().originPhone(newCall.getOriginNumber()).destinationPhone(newCall.getDestinationNumber()).duration(newCall.getDuration()).date(newCall.getDate()).build();
        this.callService.add(call);
    }


}
