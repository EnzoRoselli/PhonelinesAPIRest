package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domains.Rate;
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
    public Rate findByOriginAndDestination(Integer originId, Integer destinationId) throws RateDoesntExist {
        Optional<Rate> rate= this.rateRepository.findByOriginCityIdAndDestinationCityId(originId,destinationId);
        if (rate.isEmpty())throw new RateDoesntExist();
        return rate.get();
    }
}
