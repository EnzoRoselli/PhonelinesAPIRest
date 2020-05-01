package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ExceptionController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@RestController
@RequestMapping("/phoneline/")
public class PhonelineController {

    private final IPhonelineService phonelineService;

    @Autowired
    public PhonelineController(IPhonelineService phonelineService) {
        this.phonelineService = phonelineService;
    }

  @PostMapping(value="add/")
    public Phoneline add(@RequestBody @NotNull Phoneline phoneline) throws Exception {
        if(phoneline.hasNullAtribute()){
            throw new ParametersException("Parameters can´t contain null values");
        }else {
            try{
                return phonelineService.add(phoneline);
            }catch (DataAccessException ex){
                ExceptionController.phonelineAddException(ex);
            }
            return phoneline;
        }
    }
    @PostMapping("disable/")
    public Boolean disable(@RequestBody @NotNull Integer phoneNumber) throws ParametersException {
        if (phoneNumber==null){
            throw  new ParametersException("Parameters can´t contain null values");
        }else{
            return phonelineService.disable(phoneNumber);
        }
    }
    @PostMapping("enable/")
    public Boolean enable(@RequestBody @NotNull Integer phoneNumber) throws ParametersException {
        if (phoneNumber==null){
            throw  new ParametersException("Parameters can´t contain null values");
        }else{
            return phonelineService.enable(phoneNumber);
        }
    }

}
