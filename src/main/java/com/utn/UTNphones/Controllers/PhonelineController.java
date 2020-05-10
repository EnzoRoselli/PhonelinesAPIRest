package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/phoneline/")
public class PhonelineController {

    private final IPhonelineService phonelineService;
    private final ICityService cityService;

    @Autowired
    public PhonelineController(IPhonelineService phonelineService, ICityService cityService) {
        this.phonelineService = phonelineService;
        this.cityService = cityService;
    }

    @PostMapping(value = "add/")
    public Phoneline add(@RequestBody @NotNull Phoneline phoneline) throws Exception {
        if (phoneline.hasNullAtribute()) throw new ParametersException("Parameters can´t contain null values");
        if (phoneline.hasNumberError())throw new ParametersException("The positive number must contains 6-8 digits");
        if (!phoneline.validNumberWithPrefix(cityService.getById(phoneline.getCity().getId()).getPrefix()))throw new PhonelineExceptions("The prefix plus the numbers, are more or less than 10 digits");
        if (this.phonelineService.exists(phoneline.getNumber(),phoneline.getCity().getId())) throw new PhonelineExceptions("The phoneline already exists");
        try {
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return phoneline;
    }

    @PostMapping(value = "remove/")
    public void remove(@RequestBody @NotNull Integer phonelineId) throws Exception {
        if (phonelineId==null) throw new ParametersException("Parameters can´t contain null values");

        phonelineService.remove(phonelineId);}



    @PutMapping("disable/")
    public Boolean disable(@RequestBody @NotNull String phoneNumber) throws ParametersException {
        if (phoneNumber == null) throw new ParametersException("Parameters can´t contain null values");

        else return phonelineService.disable(phoneNumber);}

    @PutMapping("enable/")
    public Boolean enable(@RequestBody @NotNull String phoneNumber) throws ParametersException {
        if (phoneNumber == null) throw new ParametersException("Parameters can´t contain null values");

        else return phonelineService.enable(phoneNumber);}

}

