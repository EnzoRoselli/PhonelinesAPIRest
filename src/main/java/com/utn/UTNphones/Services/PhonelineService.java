package com.utn.UTNphones.Services;

import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    public Boolean disable(Integer phoneNumber) {
        if (phonelineRepository.disableOrEnable(false, phoneNumber) == 1) return true;
        else return false;
    }

    @Override
    public Boolean enable(Integer phoneNumber) {
        if (phonelineRepository.disableOrEnable(true, phoneNumber) == 1) return true;
        else return false;
    }

    @Override
    public void remove(Integer phonelineId){
        phonelineRepository.deleteById(phonelineId);
    }


}
