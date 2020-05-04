package com.utn.UTNphones.Services;

import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Repositories.IRateRepository;
import com.utn.UTNphones.Services.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService implements IRateService {

    private final IRateRepository rateRepository;

    @Autowired
    public RateService(IRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }
}
