package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;

import java.util.List;

public interface IPhonelineService {
    Phoneline add(Phoneline phoneline);

    void disable(String phoneNumber);

    void enable(String phoneNumber);

    List<Phoneline> findByUserId(Integer userId) throws PhonelinesNotRegisteredByUser;

    Phoneline findByNumber(String userId) throws PhonelineDoesntExist;

    void removeByNumber(String phoneNumber);

    Boolean exists(String number, Integer cityId);


    Phoneline getById(Integer id) throws PhonelineDoesntExist;
}
