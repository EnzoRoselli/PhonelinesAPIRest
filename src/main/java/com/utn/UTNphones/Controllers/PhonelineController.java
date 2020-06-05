package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.PhonelineRegisterDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PhonelineController {

    private final PhonelineService phonelineService;
    private final CityService cityService;

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

    public void remove(Integer phoneId) {
        phonelineService.getById(phoneId);
        phonelineService.removeById(phoneId);
    }

    public Phoneline update(Integer id, Phoneline phoneline) throws Exception {
        this.phonelineService.getById(id);
        phoneline.setId(id);
        try {
            return this.phonelineService.update(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return null;
    }

}

