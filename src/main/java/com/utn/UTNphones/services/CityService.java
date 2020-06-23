package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.City;
import com.utn.UTNphones.exceptions.cityExceptions.CityNotExists;
import com.utn.UTNphones.repositories.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final ICityRepository cityRepository;

    public City getById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(CityNotExists::new);
    }
}
