package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Services.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class RateController {

    private final IRateService rateService;

    @Autowired
    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }


    public List<Rate> getAllRates(){
        return rateService.getAllRates();
    }

    public Optional<Rate> getByOriginAndDestination(Integer originCityIdInteger, Integer destinationCityId) throws ParametersException {

        if (originCityIdInteger==null || destinationCityId==null){
            throw new ParametersException("The cities id`s must not be null");
        }
        return this.rateService.findByOriginAndDestination(originCityIdInteger,destinationCityId);
    }
}
