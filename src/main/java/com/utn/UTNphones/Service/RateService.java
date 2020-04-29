package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IRateRepository;
import com.utn.UTNphones.Service.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService implements IRateService {

    private final IRateRepository rateRepository;

    @Autowired
    public RateService(IRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
}
