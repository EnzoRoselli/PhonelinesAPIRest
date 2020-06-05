package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Repositories.IRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final IRateRepository rateRepository;

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    public Rate findByOriginAndDestination(Integer originId, Integer destinationId) throws RateDoesntExist {
        Optional<Rate> rate = this.rateRepository.findByOriginCityIdAndDestinationCityId(originId, destinationId);
        if (rate.isEmpty()) throw new RateDoesntExist();
        return rate.get();
    }

    public Rate findById(Integer id) throws RateDoesntExist {
        Optional<Rate> rate = this.rateRepository.findById(id);
        if (rate.isEmpty()) throw new RateDoesntExist();
        else return rate.get();
    }

    public List<Rate> findByOrigin(Integer originId) {
        return this.rateRepository.findByOriginCityId(originId);
    }

    public List<Rate> findByDestination(Integer destinationId) {
        return this.rateRepository.findByDestinationCityId(destinationId);
    }
}
