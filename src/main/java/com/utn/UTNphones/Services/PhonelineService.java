package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public Phoneline update(Phoneline phoneline) {
        return this.phonelineRepository.save(phoneline);
    }

    @Override
    public void removeByNumber(String phoneNumber) {
        this.phonelineRepository.removeByNumber(phoneNumber);
    }

    @Override
    public Boolean exists(String number, Integer cityId) {
        Phoneline ph = this.phonelineRepository.findByNumberAndCityId(number, cityId);
        return ph != null;
    }


}
