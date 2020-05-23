package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.ProvinceExceptions.ProvinceDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Dto.CityTop;
import com.utn.UTNphones.Models.Dto.NewCallDto;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Services.interfaces.ICallService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import com.utn.UTNphones.Services.interfaces.IProvineService;
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
<<<<<<< HEAD
    public List<Call> getCallsByUserId( Integer userId) throws UserExceptions, CallException, PhonelineExceptions {
=======
    public List<Call> getCallsByUserId(Integer userId) throws UserExceptions, CallException, PhonelineExceptions, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
>>>>>>> 781420d806f66aa37d9ef5b7e326e75b877fd551
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        return callService.getCallsByPhoneNumbers(phoneLines);
    }

    public List<CityTop> getTopDestinationsCalled(Integer userId) throws  CallException {
<<<<<<< HEAD
        return this.callService.getTopMostCalledCities(userId);
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDates dates){
=======
        List<CityTop> listWithoutFormat = this.callService.getTopMostCalledCities(userId);

        return listWithoutFormat;
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDates dates) {

>>>>>>> 781420d806f66aa37d9ef5b7e326e75b877fd551
        return this.callService.getByUserAndBetweenDates(userId,dates);
    }

    public void registerCall(NewCallDto newCall) throws CallException {
        Call call=Call.builder().originPhone(newCall.getOriginNumber()).destinationPhone(newCall.getDestinationNumber()).duration(newCall.getDuration()).date(newCall.getDate()).build();
        this.callService.add(call);
    }


}
