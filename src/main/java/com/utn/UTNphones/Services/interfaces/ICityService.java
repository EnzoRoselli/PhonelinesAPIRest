package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;

public interface ICityService {
    City getById(Integer id) throws CityDoesntExist;
}
