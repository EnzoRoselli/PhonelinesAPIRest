package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Repositories.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final ICityRepository cityRepository;

    public City getById(Integer id) throws CityDoesntExist {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isEmpty()) throw new CityDoesntExist();
        return cityOptional.get();
    }
}
