package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Models.Rate;

import java.util.List;
import java.util.Optional;

public interface IRateService {

    List<Rate> getAllRates();

    Optional<Rate> findByOriginAndDestination(Integer originId,Integer destinationId);
}
