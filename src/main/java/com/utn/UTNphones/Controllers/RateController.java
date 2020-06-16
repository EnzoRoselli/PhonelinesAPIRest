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

    public Rate getByOriginAndDestination(Integer originCityIdId, Integer destinationCityId) {
        rateService.findById(originCityIdId);
        rateService.findById(destinationCityId);
        return this.rateService.findByOriginAndDestination(originCityIdId, destinationCityId);
    }

    public List<Rate> getByOrigin(Integer originCityId) {
        rateService.findById(originCityId);
        return this.rateService.findByOrigin(originCityId);
    }

    public List<Rate> getByDestination(Integer destinationCityId) {
        rateService.findById(destinationCityId);
        return this.rateService.findByDestination(destinationCityId);
    }

    public Rate findById(Integer id) {
        return rateService.findById(id);
    }
}
