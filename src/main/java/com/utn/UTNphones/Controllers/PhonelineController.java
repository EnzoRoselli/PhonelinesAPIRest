package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.IlegalUserForPhoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import java.util.Objects;

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
        return null;
    }

    public void remove(Integer phoneId) {
        phonelineService.getById(phoneId);
        phonelineService.removeById(phoneId);
    }

    public Phoneline update(Integer id, PhonelineDTO phonelineDto) throws Exception {
        this.phonelineService.getById(id);
        Phoneline phoneline = Phoneline.fromDto(phonelineDto);
        phoneline.setId(id);
        try {
            return this.phonelineService.update(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineUpdateException(Objects.requireNonNull(ex.getRootCause()));
        }
        return null;
    }

}

