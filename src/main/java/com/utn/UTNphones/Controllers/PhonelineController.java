package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.PhonelineRegisterDTO;
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

    public Phoneline add(PhonelineRegisterDTO phonelineRegisterDto) throws Exception {
        if (!phonelineRegisterDto.validNumberWithPrefix(cityService.getById(phonelineRegisterDto.getCityId()).getPrefix()))
            throw new PhonelineDigitsCountPlusPrefix();
        try {
            Phoneline phoneline = new Phoneline(phonelineRegisterDto);
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return null;
    }

    public void remove(Integer phoneId) throws PhonelineDoesntExist {
        phonelineService.getById(phoneId);
        phonelineService.removeById(phoneId);
    }

    public Phoneline update(Integer id,Phoneline phoneline) throws Exception {
        this.phonelineService.getById(id);
        phoneline.setId(id);
        try {
            return this.phonelineService.update(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineUpdateException(ex.getRootCause());
        }
        return null;
    }

}

