package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

@Controller
public class PhonelineController {

    private final IPhonelineService phonelineService;
    private final ICityService cityService;

    @Autowired
    public PhonelineController(IPhonelineService phonelineService, ICityService cityService) {
        this.phonelineService = phonelineService;
        this.cityService = cityService;
    }

    public Phoneline getById(Integer id) throws PhonelineDoesntExist {
       return this.phonelineService.getById(id);
    }

    public Phoneline add(@RequestBody @NotNull Phoneline phoneline) throws Exception {
        if (phoneline.hasNullAtribute()) throw new ParametersException("Parameters can´t contain null values");
        if (!phoneline.validNumberWithPrefix(cityService.getById(phoneline.getCity().getId()).getPrefix()))
            throw new PhonelineDigitsCountPlusPrefix();
        try {
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return phoneline;
    }


    public void remove(@RequestBody @NotNull String phoneNumber) throws Exception {
        if (phoneNumber == null) throw new ParametersException("Parameters can´t contain null values");
        phonelineService.findByNumber(phoneNumber);
        phonelineService.removeByNumber(phoneNumber);
    }


    public Boolean disable(@RequestBody @NotNull String phoneNumber) throws ParametersException, PhonelineExceptions {
        if (phoneNumber == null) throw new ParametersException("Parameters can´t contain null values");
        phonelineService.findByNumber(phoneNumber);
        return phonelineService.disable(phoneNumber);
    }


    public Boolean enable(@RequestBody @NotNull String phoneNumber) throws ParametersException, PhonelineExceptions {
        if (phoneNumber == null) throw new ParametersException("Parameters can´t contain null values");
        phonelineService.findByNumber(phoneNumber);
        return phonelineService.enable(phoneNumber);
    }

}

