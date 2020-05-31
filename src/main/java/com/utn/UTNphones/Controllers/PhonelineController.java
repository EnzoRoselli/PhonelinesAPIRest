package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.PhonelineAddDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Services.interfaces.ICityService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

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

    public Phoneline add(PhonelineAddDTO phonelineAddDto) throws Exception {
        if (!phonelineAddDto.validNumberWithPrefix(cityService.getById(phonelineAddDto.getCityId()).getPrefix()))
            throw new PhonelineDigitsCountPlusPrefix();
        try {
            Phoneline phoneline = new Phoneline(phonelineAddDto);
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return null;
    }

    public void remove(String phoneNumber) throws PhonelineDoesntExist {
        phonelineService.findByNumber(phoneNumber);
        phonelineService.removeByNumber(phoneNumber);
    }

    public Phoneline update(Integer id,Phoneline phoneline) throws Exception {
        this.phonelineService.getById(id);
        phoneline.setId(id);
        try {
            return this.phonelineService.update(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineUpdateException(ex);
        }
        return null;
    }

}

