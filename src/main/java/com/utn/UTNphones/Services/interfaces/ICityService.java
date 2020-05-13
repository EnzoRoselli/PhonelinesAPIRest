package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.CityExceptions.CityExceptions;
import com.utn.UTNphones.Models.City;

public interface ICityService {
    City getById(Integer id) throws CityExceptions;
}
