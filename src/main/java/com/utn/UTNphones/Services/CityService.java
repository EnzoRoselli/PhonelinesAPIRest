package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Repositories.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final ICityRepository cityRepository;

    public City getById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(CityDoesntExist::new);
    }
}
