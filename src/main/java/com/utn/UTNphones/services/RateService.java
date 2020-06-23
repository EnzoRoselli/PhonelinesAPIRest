package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.Rate;
import com.utn.UTNphones.exceptions.rateExceptions.RateNotExists;
import com.utn.UTNphones.repositories.IRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final IRateRepository rateRepository;

    private final Pageable pageable = PageRequest.of(0, 10);

    public List<Rate> getAllRates() {
        Page page = rateRepository.findAll(pageable);
        return page.getContent();
    }

    public Rate findByOriginAndDestination(Integer originId, Integer destinationId) {
        return this.rateRepository.findByOriginCityIdAndDestinationCityId(originId, destinationId)
                .orElseThrow(RateNotExists::new);

    }

    public Rate findById(Integer id) {
        return this.rateRepository.findById(id)
                .orElseThrow(RateNotExists::new);
    }

    public List<Rate> findByOrigin(Integer originId) {
        return this.rateRepository.findByOriginCityId(originId);
    }

    public List<Rate> findByDestination(Integer destinationId) {
        return this.rateRepository.findByDestinationCityId(destinationId);
    }
}
