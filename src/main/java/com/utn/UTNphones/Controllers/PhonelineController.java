package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
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
import java.util.Optional;

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
        if (phoneline.hasNullAttribute()) throw new ParametersException("Parameters can´t contain null values");
        if (!phoneline.validNumberWithPrefix(cityService.getById(phoneline.getCity().getId()).getPrefix()))
            throw new PhonelineDigitsCountPlusPrefix();
        try {
            return phonelineService.add(phoneline);
        } catch (DataAccessException ex) {
            ExceptionController.phonelineAddException(ex);
        }
        return phoneline;
    }


    public void remove(String phoneNumber) throws PhonelineDoesntExist {
        phonelineService.findByNumber(phoneNumber);
        phonelineService.removeByNumber(phoneNumber);
    }



//    public void disableOrEnable(String phoneNumber) throws PhonelineDoesntExist {
//        Phoneline p = phonelineService.findByNumber(phoneNumber);
//
//        if (p.getStatus()) {
//            phonelineService.disable(phoneNumber);
//        } else {
//            phonelineService.enable(phoneNumber);
//        }
//    }

//    public Boolean disable(@RequestBody @NotNull String phoneNumber) throws PhonelineDoesntExist {
//        phonelineService.findByNumber(phoneNumber);
//        return phonelineService.disable(phoneNumber);
//    }
//
//
//    public Boolean enable(@RequestBody @NotNull String phoneNumber) throws PhonelineDoesntExist {
//        phonelineService.findByNumber(phoneNumber);
//        return phonelineService.enable(phoneNumber);
//    }

    public User update(Integer id,Phoneline user) throws Exception {
        User inDataBaseUser = this.userService.findById(user.getId());
        user=setNonNullValues(user,inDataBaseUser);
        try {
            return this.userService.update(user);
        } catch (DataAccessException ex) {
            ExceptionController.userUpdateException(ex);
        }
        return user;
    }

    private User setNonNullValues(User newUser,User oldUser) {
        Optional.ofNullable(newUser.getPassword()).ifPresent(oldUser::setPassword);
        Optional.ofNullable(newUser.getIdentification()).ifPresent(oldUser::setIdentification);
        Optional.ofNullable(newUser.getCity()).ifPresent(oldUser::setCity);
        Optional.ofNullable(newUser.getLastname()).ifPresent(oldUser::setLastname);
        Optional.ofNullable(newUser.getName()).ifPresent(oldUser::setName);
        Optional.ofNullable(newUser.getType()).ifPresent(oldUser::setType);
        return oldUser;
    }

}

