package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Models.Phoneline;
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
        if (phonelineRepository.disableOrEnable(false, phoneNumber) == 1) return true;
        else return false;
    }

    public List<Phoneline> findByUserId(Integer id) throws PhonelineExceptions {
        List<Phoneline> phoneList=this.phonelineRepository.findByUserId(id);
        return Optional.ofNullable(phoneList).orElseThrow(()->new PhonelinesNotRegisteredByUser());
    }

    public Phoneline findByNumber(String number) throws PhonelineExceptions {
        Phoneline ph= phonelineRepository.findByNumber(number);
        if (ph==null) throw new PhonelineDoesntExist();
        else return ph;
    }

    @Override
    public Boolean enable(String phoneNumber) {
        if (phonelineRepository.disableOrEnable(true, phoneNumber) == 1) return true;
        else return false;
    }

    @Override
    public void removeByNumber(String phoneNumber) throws PhonelineExceptions {
        try{
        this.phonelineRepository.removeByNumber(phoneNumber);
        }catch (EmptyResultDataAccessException ex){
            throw new PhonelineDoesntExist(ex.getCause());
        }
    }

    @Override
    public Boolean exists(String number, Integer cityId) {
        Phoneline ph = this.phonelineRepository.findByNumberAndCityId(number,cityId);
        if (ph==null)return false;
        else return true;
    }


}
