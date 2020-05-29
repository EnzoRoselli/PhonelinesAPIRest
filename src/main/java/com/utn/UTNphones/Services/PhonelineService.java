package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhonelineService implements IPhonelineService {

    private final IPhonelineRepository phonelineRepository;

    @Autowired
    public PhonelineService(IPhonelineRepository phonelineRepository) {
        this.phonelineRepository = phonelineRepository;
    }

    @Override
    public Phoneline add(Phoneline phoneline) throws DataAccessException {
        return phonelineRepository.save(phoneline);
    }

    @Override
    public Boolean disable(String phoneNumber) {
        return phonelineRepository.disableOrEnable(false, phoneNumber) == 1;
    }

    public List<Phoneline> findByUserId(Integer id) throws PhonelinesNotRegisteredByUser {
        List<Phoneline> phoneList = this.phonelineRepository.findByUserId(id);
        if (phoneList.isEmpty()){ throw new PhonelinesNotRegisteredByUser();}
        return phoneList;
    }

    public Phoneline findByNumber(String number) throws PhonelineDoesntExist {
        Phoneline ph = phonelineRepository.findByNumber(number);
        if (ph == null) throw new PhonelineDoesntExist();
         return ph;
    }
    public Phoneline getById(Integer id) throws PhonelineDoesntExist {
        Optional<Phoneline> ph = phonelineRepository.findById(id);
        if (ph.isEmpty()){throw new PhonelineDoesntExist();}
        return ph.get();
    }

    @Override
    public Boolean enable(String phoneNumber) {
        return phonelineRepository.disableOrEnable(true, phoneNumber) == 1;
    }

    @Override
    public void removeByNumber(String phoneNumber) throws PhonelineDoesntExist {
        //todo primero llamar por numero y luego deletear , sacar el catch en advice controllah too
        try {
            this.phonelineRepository.removeByNumber(phoneNumber);
        } catch (EmptyResultDataAccessException ex) {
            throw new PhonelineDoesntExist();
        }
    }

    @Override
    public Boolean exists(String number, Integer cityId) {
        Phoneline ph = this.phonelineRepository.findByNumberAndCityId(number, cityId);
        return ph != null;
    }


}
