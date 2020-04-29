package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.ICityRepository;
import com.utn.UTNphones.Service.interfaces.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    private final ICityRepository cityRepository;

    @Autowired
    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
