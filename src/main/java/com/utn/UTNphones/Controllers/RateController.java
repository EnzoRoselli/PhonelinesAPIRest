package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    public Rate getByOriginAndDestination(Integer originCityIdInteger, Integer destinationCityId) throws RateDoesntExist {
        return this.rateService.findByOriginAndDestination(originCityIdInteger, destinationCityId);
    }
}
