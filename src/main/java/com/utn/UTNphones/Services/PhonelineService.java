package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhonelineService {

    private final IPhonelineRepository phonelineRepository;

    public Phoneline add(Phoneline phoneline) throws DataAccessException {
        return phonelineRepository.save(phoneline);
    }

    public List<Phoneline> findByUserId(Integer id) throws PhonelinesNotRegisteredByUser {
        List<Phoneline> phoneList = this.phonelineRepository.findByUserId(id);
        if (phoneList.isEmpty()) {
            throw new PhonelinesNotRegisteredByUser();
        }
        return phoneList;
    }

    public Phoneline findByNumber(String number) throws PhonelineDoesntExist {
        Phoneline ph = phonelineRepository.findByNumber(number);
        if (ph == null) throw new PhonelineDoesntExist();
        return ph;
    }

    public Phoneline getById(Integer id) throws PhonelineDoesntExist {
        Optional<Phoneline> ph = phonelineRepository.findById(id);
        if (ph.isEmpty()) {
            throw new PhonelineDoesntExist();
        }
        return ph.get();
    }

    public Phoneline update(Phoneline phoneline) {
        return this.phonelineRepository.save(phoneline);
    }

    public void removeById(Integer phoneId) {
        this.phonelineRepository.deleteById(phoneId);
    }

    public Boolean exists(String number, Integer cityId) {
        Phoneline ph = this.phonelineRepository.findByNumberAndCityId(number, cityId);
        return ph != null;
    }


}
