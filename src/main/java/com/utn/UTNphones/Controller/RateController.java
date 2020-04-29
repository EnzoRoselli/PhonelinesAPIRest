package com.utn.UTNphones.Controller;

import com.utn.UTNphones.Service.interfaces.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rate")
public class RateController {

    private final IRateService rateService;

    @Autowired
    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }
}
