package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Services.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rate/")
public class RateController {

    private final IRateService rateService;

    @Autowired
    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }

    @RequestMapping("")
    public List<Rate> getAllRates(){
        return rateService.getAllRates();
    }
}
