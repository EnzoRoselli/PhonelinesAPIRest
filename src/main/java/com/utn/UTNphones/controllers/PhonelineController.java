package com.utn.UTNphones.controllers;

import com.utn.UTNphones.domains.dto.requests.PhonelineDTO;
import com.utn.UTNphones.domains.Phoneline;
import com.utn.UTNphones.exceptions.phonelineExceptions.IlegalUserForPhoneline;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.services.CityService;
import com.utn.UTNphones.services.PhonelineService;
import com.utn.UTNphones.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PhonelineController {

    private final PhonelineService phonelineService;
    private final CityService cityService;
    private final UserService userService;

    public Phoneline getById(Integer id) {
        return this.phonelineService.getById(id);
    }

    public Phoneline add(PhonelineDTO phonelineDto) throws Exception {
        if (!phonelineDto.validNumberWithPrefix(cityService.getById(phonelineDto.getCityId()).getPrefix()))
            throw new PhonelineDigitsCountPlusPrefix();
        if (!"client".equals(userService.findById(phonelineDto.getUserId()).getType()))
            throw new IlegalUserForPhoneline();
        try {
            return phonelineService.add(Phoneline.fromDto(phonelineDto));
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return Phoneline.fromDto(phonelineDto);
    }

    public Phoneline remove(Integer phoneId) {
        Phoneline ph = phonelineService.getById(phoneId);
        phonelineService.removeById(phoneId);
        return ph;
    }

    public Phoneline update(Integer id, PhonelineDTO phonelineDto) throws Exception {
        this.phonelineService.getById(id);
        Phoneline phoneline = Phoneline.fromDto(phonelineDto);
        phoneline.setId(id);
        try {
            return this.phonelineService.update(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineUpdateException(ex.getRootCause());
        }
        return Phoneline.fromDto(phonelineDto);
    }

}

