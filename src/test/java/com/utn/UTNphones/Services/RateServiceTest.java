package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domain.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domain.City;
import com.utn.UTNphones.Domain.Rate;
import com.utn.UTNphones.Repositories.IRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateServiceTest {
    RateService rateService;
    @Mock
    IRateRepository rateRepository;

    @Before
    public void setUp(){
        initMocks(this);
        rateService = new RateService(rateRepository);
    }

    @Test
    public void testGetAllRatesOk() {
        City city1 = City.builder().id(1).build();
        City city2 = City.builder().id(2).build();
        List<Rate> rates= new ArrayList<>();
        Rate rate=Rate.builder().originCity(city1).destinationCity(city2).build();
        Rate rate2=Rate.builder().originCity(city2).destinationCity(city1).build();
        rates.add(rate2);
        rates.add(rate);
        when(rateRepository.findAll()).thenReturn(rates);
        List<Rate> ratesFromDb=rateService.getAllRates();
        assertEquals(ratesFromDb,rates);
    }

    @Test
    public void testFindByOriginAndDestinationOk() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate=Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(),rate.getDestinationCity().getId())).thenReturn(java.util.Optional.of(rate));
        Rate rateFromDb=rateService.findByOriginAndDestination(rate.getOriginCity().getId(),rate.getDestinationCity().getId());
        assertEquals(rateFromDb,rate);
    }

    @Test(expected = RateDoesntExist.class)
    public void testFindByOriginAndDestinationException() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate=Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        Optional<Rate> x = Optional.empty();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(x);
         rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
    }
}
