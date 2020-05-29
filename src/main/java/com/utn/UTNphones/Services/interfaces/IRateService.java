package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;

import java.util.List;

public interface IRateService {

    List<Rate> getAllRates();

    Rate findByOriginAndDestination(Integer originId, Integer destinationId) throws RateDoesntExist;
}
