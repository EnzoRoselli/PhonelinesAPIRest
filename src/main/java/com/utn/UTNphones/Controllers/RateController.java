package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Rate;
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

    public Rate getByOriginAndDestination(Integer originCityIdInteger, Integer destinationCityId) {
        this.rateService.findById(originCityIdInteger);
        this.rateService.findById(destinationCityId);
        return this.rateService.findByOriginAndDestination(originCityIdInteger, destinationCityId);
    }

    public List<Rate> getByOrigin(Integer originCityId) {
        this.rateService.findById(originCityId);

        return this.rateService.findByOrigin(originCityId);
    }

    public List<Rate> getByDestination(Integer destinationCityId) {
        this.rateService.findById(destinationCityId);
        return this.rateService.findByDestination(destinationCityId);
    }
}
