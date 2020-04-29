package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IProvinceRepository;
import com.utn.UTNphones.Service.interfaces.IProvineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService implements IProvineService {

    private final IProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(IProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }
}
