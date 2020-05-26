package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domain.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domain.Rate;
import com.utn.UTNphones.Services.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RateController {

    private final IRateService rateService;

    @Autowired
    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }


    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    public Rate getByOriginAndDestination(Integer originCityIdInteger, Integer destinationCityId) throws RateDoesntExist {
        return this.rateService.findByOriginAndDestination(originCityIdInteger, destinationCityId);
    }
}
