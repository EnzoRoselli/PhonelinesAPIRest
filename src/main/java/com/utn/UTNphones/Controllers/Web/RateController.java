package com.utn.UTNphones.Controllers.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phonelineManagement")
public class RateController {

    private final RateController rateController;

    @Autowired
    public RateController(RateController rateController) {
        this.rateController = rateController;
    }

    @GetMapping
    public 
}
