package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.RateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateControllerTest {

    RateController rateController;
    @Mock
    RateService rateService;
    @Mock
    CityService cityService;

    @Before
    public void setUp() {
        initMocks(this);
        rateController = new RateController(rateService, cityService);
    }

    @Test
    public void getAllRatesOk() {
        City city1 = City.builder().id(1).build();
        City city2 = City.builder().id(2).build();
        List<Rate> rates = new ArrayList<>();
        Rate rate = Rate.builder().originCity(city1).destinationCity(city2).build();
        Rate rate2 = Rate.builder().originCity(city2).destinationCity(city1).build();
        rates.add(rate2);
        rates.add(rate);
        when(rateService.getAllRates()).thenReturn(rates);
        List<Rate> ratesList = rateController.getAllRates();

        assertEquals(ratesList, rates);
    }

    @Test
    public void getByOriginAndDestinationOk() {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate = Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        when(rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(rate);
        Rate rateFromController = rateController.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
        assertEquals(rateFromController, rate);
    }

    @Test(expected = RateDoesntExist.class)
    public void testFindByOriginAndDestinationException() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate = Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        when(cityService.getById(rate.getOriginCity().getId())).thenThrow(new RateDoesntExist());
        when(cityService.getById(rate.getDestinationCity().getId())).thenReturn(destinationCity);
        rateController.findByOriginAndDestination(1, 2);
    }

    @Test
    public void getByOriginOk() {
        City originCity = City.builder().id(1).build();
        City destinationCity1 = City.builder().id(2).build();
        City destinationCity2 = City.builder().id(3).build();
        Rate rate1 = Rate.builder().originCity(originCity).destinationCity(destinationCity1).build();
        Rate rate2 = Rate.builder().originCity(originCity).destinationCity(destinationCity2).build();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate1);
        rates.add(rate2);
        when(rateService.findByOrigin(originCity.getId())).thenReturn(rates);
        List<Rate> ratesFromController = rateController.findByOrigin(originCity.getId());
        assertEquals(ratesFromController, rates);
    }

    @Test
    public void getByDestinationOk() {
        City originCity1 = City.builder().id(1).build();
        City originCity2 = City.builder().id(2).build();
        City destinationCity = City.builder().id(3).build();
        Rate rate1 = Rate.builder().originCity(originCity1).destinationCity(destinationCity).build();
        Rate rate2 = Rate.builder().originCity(originCity2).destinationCity(destinationCity).build();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate1);
        rates.add(rate2);
        when(rateService.findByDestination(destinationCity.getId())).thenReturn(rates);
        List<Rate> ratesFromController = rateController.findByDestination(destinationCity.getId());
        assertEquals(ratesFromController, rates);
    }

    @Test
    public void getByIdOk() {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(3).build();
        Rate rate1 = Rate.builder().id(2).originCity(originCity).destinationCity(destinationCity).build();
        when(rateService.findById(rate1.getId())).thenReturn(rate1);
        Rate rateFromController = rateController.findById(rate1.getId());
        assertEquals(rateFromController, rate1);
    }

}
