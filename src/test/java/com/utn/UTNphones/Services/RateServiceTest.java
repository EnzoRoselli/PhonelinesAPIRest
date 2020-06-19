package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Repositories.IRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public void setUp() {
        initMocks(this);
        rateService = new RateService(rateRepository);
    }

    @Test
    public void testGetAllRatesOk() {
        City city1 = City.builder().id(1).build();
        City city2 = City.builder().id(2).build();
        Rate rate = Rate.builder().originCity(city1).destinationCity(city2).build();
        Rate rate2 = Rate.builder().originCity(city2).destinationCity(city1).build();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate2);
        rates.add(rate);
        Page<Rate> page = new PageImpl<>(rates);
        Pageable pageable = PageRequest.of(0, 10);
        when(rateRepository.findAll(pageable)).thenReturn(page);
        List<Rate> ratesFromDb = rateService.getAllRates();
        assertEquals(ratesFromDb, rates);
    }

    @Test
    public void testFindByOriginAndDestinationOk() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate = Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(java.util.Optional.of(rate));
        Rate rateFromDb = rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
        assertEquals(rateFromDb, rate);
    }

    @Test(expected = RateDoesntExist.class)
    public void testFindByOriginAndDestinationException() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate = Rate.builder().originCity(originCity).destinationCity(destinationCity).build();
        Optional<Rate> x = Optional.empty();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(x);
        rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
    }

    @Test
    public void testFindByIdOk() throws RateDoesntExist {
        City originCity = City.builder().id(1).build();
        City destinationCity = City.builder().id(2).build();
        Rate rate = Rate.builder().id(2).originCity(originCity).destinationCity(destinationCity).build();
        Integer rateId = 2;
        when(rateRepository.findById(rateId)).thenReturn(java.util.Optional.of(rate));
        Rate rateFromDb = rateService.findById(rateId);
        assertEquals(rateFromDb, rate);
    }

    @Test(expected = RateDoesntExist.class)
    public void testFindByIdOkException() throws RateDoesntExist {
        Integer rateId = 2;
        when(rateRepository.findById(rateId)).thenReturn(Optional.empty());
        rateService.findById(rateId);
    }

    @Test
    public void testFindByOrigin() {
        City originCity = City.builder().id(1).build();
        City destinationCity1 = City.builder().id(2).build();
        City destinationCity2 = City.builder().id(3).build();
        List<Rate> rates = new ArrayList<>();
        rates.add(Rate.builder().originCity(originCity).destinationCity(destinationCity1).build());
        rates.add(Rate.builder().originCity(originCity).destinationCity(destinationCity2).build());
        when(rateRepository.findByOriginCityId(originCity.getId())).thenReturn(rates);
        List<Rate> ratesFromDb = rateService.findByOrigin(originCity.getId());
        assertEquals(ratesFromDb, rates);
    }

    @Test
    public void testFindByDestination() {
        City originCity1 = City.builder().id(1).build();
        City originCity2 = City.builder().id(2).build();
        City destinationCity = City.builder().id(3).build();
        List<Rate> rates = new ArrayList<>();
        rates.add(Rate.builder().originCity(originCity1).destinationCity(destinationCity).build());
        rates.add(Rate.builder().originCity(originCity2).destinationCity(destinationCity).build());
        when(rateRepository.findByDestinationCityId(destinationCity.getId())).thenReturn(rates);
        List<Rate> ratesFromDb = rateService.findByDestination(destinationCity.getId());
        assertEquals(ratesFromDb, rates);
    }
}
