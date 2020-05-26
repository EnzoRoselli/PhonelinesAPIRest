package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domain.Exceptions.CityExceptions.CityExceptions;
import com.utn.UTNphones.Domain.City;

public interface ICityService {
    City getById(Integer id) throws CityExceptions;
}
