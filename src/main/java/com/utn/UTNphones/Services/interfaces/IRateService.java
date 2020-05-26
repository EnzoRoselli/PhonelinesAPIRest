package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domain.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domain.Rate;

import java.util.List;

public interface IRateService {

    List<Rate> getAllRates();

    Rate findByOriginAndDestination(Integer originId, Integer destinationId) throws RateDoesntExist;
}
