package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.Phoneline;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineNotExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.repositories.IPhonelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhonelineService {

    private final IPhonelineRepository phonelineRepository;

    public Phoneline add(Phoneline phoneline) throws DataAccessException {
        return phonelineRepository.save(phoneline);
    }

    public List<Phoneline> findByUserId(Integer id) {
        List<Phoneline> phoneList = this.phonelineRepository.findByUserId(id);
        if (phoneList.isEmpty()) {
            throw new PhonelinesNotRegisteredByUser();
        }
        return phoneList;
    }

    public Phoneline findByNumber(String number) {
        return phonelineRepository.findByNumber(number)
                .orElseThrow(PhonelineNotExists::new);
    }

    public Phoneline getById(Integer id) {
        return phonelineRepository.findById(id)
                .orElseThrow(PhonelineNotExists::new);
    }

    public Phoneline update(Phoneline phoneline) {
        return this.phonelineRepository.save(phoneline);
    }

    public void removeById(Integer phoneId) {
        this.phonelineRepository.deleteById(phoneId);
    }

}
