package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.ProvinceExceptions.ProvinceDoesntExist;
import com.utn.UTNphones.Models.Province;
import com.utn.UTNphones.Repositories.IProvinceRepository;
import com.utn.UTNphones.Services.interfaces.IProvineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvineService {

    private final IProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(IProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public Optional<Province> getById(Integer id) throws ProvinceDoesntExist {
        Optional<Province> province= this.provinceRepository.findById(id);
        return Optional.ofNullable(province).orElseThrow(ProvinceDoesntExist::new);
    }
}
