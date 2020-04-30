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
    public Phoneline add(@RequestBody @NotNull Phoneline phoneline) throws ParametersException {
        if(phoneline.hasNullAtribute()){
            throw new ParametersException();
        }else {
            try{
                Phoneline a=phonelineService.add(phoneline);
                System.out.println(a.getCity().getPrefix());
                return a;
            }catch (SQLGrammarException ex){
                ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
                //ExceptionController.addPhonelineException(cve.getSQLException().getErrorCode());
            }
            return phoneline;
        }

    }
}
