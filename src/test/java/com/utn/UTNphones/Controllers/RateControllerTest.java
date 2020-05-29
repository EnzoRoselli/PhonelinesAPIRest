package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.RateService;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateControllerTest {

    RateController rateController;
    @Mock
    RateService rateService;

    @Before
    public void setUp(){
        initMocks(this);
        rateController = new RateController(rateService);
    }

    @Test
    public void getAllRatesOk() {
        City city1 = City.builder().id(1).build();
        City city2 = City.builder().id(2).build();
        List<Rate> rates= new ArrayList<>();
        Rate rate=Rate.builder().originCity(city1).destinationCity(city2).build();
        Rate rate2=Rate.builder().originCity(city2).destinationCity(city1).build();
        rates.add(rate2);
        rates.add(rate);

        when(rateService.getAllRates()).thenReturn(rates);
        List<Rate> ratesList = rateController.getAllRates();

        assertEquals(ratesList, rates);
    }

    @Test
    public void getByOriginAndDestinationOk() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate=Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        when(rateService.findByOriginAndDestination(rate.getOriginCity().getId(),rate.getDestinationCity().getId())).thenReturn(rate);
        Rate rateFromController=rateController.getByOriginAndDestination(rate.getOriginCity().getId(),rate.getDestinationCity().getId());
        assertEquals(rateFromController,rate);
    }

    @Test(expected = RateDoesntExist.class)
    public void testFindByOriginAndDestinationException() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate=Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        Optional<Rate> x = Optional.empty();
        when(rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenThrow(new RateDoesntExist());
        rateController.getByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
    }

}
