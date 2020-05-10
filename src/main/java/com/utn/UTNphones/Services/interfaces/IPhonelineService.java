package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.PhonelineExceptions;
import com.utn.UTNphones.Models.Phoneline;

import java.util.List;

public interface IPhonelineService {
    Phoneline add(Phoneline phoneline);

    Boolean disable(String phoneNumber);

    Boolean enable(String phoneNumber);

    List<Phoneline> findByUserId(Integer userId) throws PhonelineExceptions;

    void remove(Integer phonelineId) throws PhonelineExceptions;

    Boolean exists(String number, Integer cityId);
}
