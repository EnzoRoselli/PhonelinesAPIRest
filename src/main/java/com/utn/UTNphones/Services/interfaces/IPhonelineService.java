package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;

import java.util.List;

public interface IPhonelineService {
    Phoneline add(Phoneline phoneline);

    Boolean disable(String phoneNumber);

    Boolean enable(String phoneNumber);

    List<Phoneline> findByUserId(Integer userId) throws PhonelineExceptions;

    Phoneline findByNumber(String userId) throws PhonelineExceptions;

    void removeByNumber(String phoneNumber) throws PhonelineExceptions;

    Boolean exists(String number, Integer cityId);


    Phoneline getById(Integer id) throws PhonelineDoesntExist;
}
