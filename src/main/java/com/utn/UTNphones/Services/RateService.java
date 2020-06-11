package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Repositories.IRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final IRateRepository rateRepository;

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    public Rate findByOriginAndDestination(Integer originId, Integer destinationId) {
      return this.rateRepository.findByOriginCityIdAndDestinationCityId(originId, destinationId)
              .orElseThrow(RateDoesntExist::new);

    }

    public Rate findById(Integer id) {
        return this.rateRepository.findById(id)
                .orElseThrow(RateDoesntExist::new);

    }

    public List<Rate> findByOrigin(Integer originId) {
        return this.rateRepository.findByOriginCityId(originId);
    }

    public List<Rate> findByDestination(Integer destinationId) {
        return this.rateRepository.findByDestinationCityId(destinationId);
    }
}
