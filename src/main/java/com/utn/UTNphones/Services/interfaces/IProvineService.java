package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Exceptions.ProvinceExceptions.ProvinceDoesntExist;
import com.utn.UTNphones.Models.Province;

import java.util.Optional;

public interface IProvineService {
    public Optional<Province> getById(Integer id) throws ProvinceDoesntExist;
}
