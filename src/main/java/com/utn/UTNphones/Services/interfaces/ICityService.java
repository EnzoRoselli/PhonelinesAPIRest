package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Exceptions.CityExceptions.CityExceptions;

public interface ICityService {
    City getById(Integer id) throws CityExceptions;
}
