package com.utn.UTNphones.Services;

import com.utn.UTNphones.Repositories.ICityRepository;
import com.utn.UTNphones.Services.interfaces.ICityService;
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
