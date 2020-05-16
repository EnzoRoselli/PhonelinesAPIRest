package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.ICallService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
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

    public void addCall( Call call) throws CallException {
         callService.add(call);
    }

    public List<Call> getCallsByUserId( Integer userId) throws UserExceptions, CallException, PhonelineExceptions, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        return callService.getCallsByPhoneNumbers(phoneLines);
    }

    public List<Object> getTopDestinationsCalled( Integer userId) throws UserExceptions, CallException, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
        userService.findById(userId);
        return this.callService.getTopMostCalledCities(userId);
    }

    public List<Call> getByUserBetweenDates(Integer userId, Date start, Date max) throws UserExceptions, ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }
        userService.findById(userId);
        return this.callService.getBetweenDates(start,max);
    }


}
