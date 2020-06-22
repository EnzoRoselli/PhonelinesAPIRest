package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateNotExists;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.RateService;
import com.utn.UTNphones.Utils.ObjectCreator;
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
        List<Rate> rates = new ArrayList<>();
        rates.add(ObjectCreator.createRate());
        rates.add(ObjectCreator.createRate());
        when(rateService.getAllRates()).thenReturn(rates);
        assertEquals(rateController.getAllRates(), rates);
    }

    @Test
    public void getByOriginAndDestinationOk() {
        Rate rate = ObjectCreator.createRate();
        when(rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(rate);
        Rate rateFromController = rateController.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
        assertEquals(rateFromController, rate);
    }

    @Test(expected = RateNotExists.class)
    public void testFindByOriginAndDestinationException() {
        Rate rate = ObjectCreator.createRate();
        when(cityService.getById(rate.getOriginCity().getId())).thenThrow(new RateNotExists());
        when(cityService.getById(rate.getDestinationCity().getId())).thenReturn(rate.getDestinationCity());
        rateController.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
    }

    @Test
    public void getByOriginOk() {
        List<Rate> rates = new ArrayList<>();
        rates.add(ObjectCreator.createRate());
        rates.add(ObjectCreator.createRate());
        when(rateService.findByOrigin(ObjectCreator.createCity().getId())).thenReturn(rates);
        assertEquals(rateController.findByOrigin(ObjectCreator.createCity().getId()), rates);
    }

    @Test
    public void getByDestinationOk() {
        List<Rate> rates = new ArrayList<>();
        rates.add(ObjectCreator.createRate());
        rates.add(ObjectCreator.createRate());
        when(rateService.findByDestination(ObjectCreator.createCity().getId())).thenReturn(rates);
        assertEquals(rateController.findByDestination(ObjectCreator.createCity().getId()), rates);
    }

    @Test
    public void getByIdOk() {
        Rate rate =ObjectCreator.createRate();
        when(rateService.findById(ObjectCreator.createRate().getId())).thenReturn(ObjectCreator.createRate());
        assertEquals(rateController.findById(ObjectCreator.createRate().getId()), ObjectCreator.createRate());
    }

}
