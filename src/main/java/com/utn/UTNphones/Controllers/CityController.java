package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Services.interfaces.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {

    private final ICityService cityService;

    @Autowired
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }
}
