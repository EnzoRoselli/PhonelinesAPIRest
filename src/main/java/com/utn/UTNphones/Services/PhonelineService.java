package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
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
        return Optional.ofNullable(phoneList).orElseThrow(()->new PhonelineExceptions("No phones registered with this user"));
    }

    @Override
    public Boolean enable(String phoneNumber) {
        if (phonelineRepository.disableOrEnable(true, phoneNumber) == 1) return true;
        else return false;
    }

    @Override
    public void remove(Integer phonelineId) throws PhonelineExceptions {
        try{
        this.phonelineRepository.deleteById(phonelineId);
        }catch (EmptyResultDataAccessException ex){
            throw new PhonelineExceptions("The phonenumber to delete is not registered",ex.getCause());
        }
    }

    @Override
    public Boolean exists(String number, Integer cityId) {
        return this.phonelineRepository.findByNumberAndCityId(number,cityId);
    }


}
