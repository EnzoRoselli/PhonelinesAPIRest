package com.utn.UTNphones.Services;

import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Repositories.IRateRepository;
import com.utn.UTNphones.Services.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Rate> findByOriginAndDestination(Rate rate) {
        return this.rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(),rate.getDestinationCity().getId());
    }
}
