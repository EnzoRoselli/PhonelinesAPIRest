package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.Rate;
import com.utn.UTNphones.exceptions.rateExceptions.RateNotExists;
import com.utn.UTNphones.repositories.IRateRepository;
import com.utn.UTNphones.utils.ObjectCreator;
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
        List<Rate> rates = new ArrayList<>();
        rates.add(ObjectCreator.createRate());
        rates.add(ObjectCreator.createRate());
        Page<Rate> page = new PageImpl<>(rates);
        Pageable pageable = PageRequest.of(0, 10);
        when(rateRepository.findAll(pageable)).thenReturn(page);
        assertEquals(rateService.getAllRates(), rates);
    }

    @Test
    public void testFindByOriginAndDestinationOk() throws RateNotExists {
        Rate rate = ObjectCreator.createRate();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(Optional.of(rate));
        Rate rateFromDb = rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
        assertEquals(rateFromDb, rate);
    }

    @Test(expected = RateNotExists.class)
    public void testFindByOriginAndDestinationException() throws RateNotExists {
        Rate rate = ObjectCreator.createRate();
        Optional<Rate> x = Optional.empty();
        when(rateRepository.findByOriginCityIdAndDestinationCityId(rate.getOriginCity().getId(), rate.getDestinationCity().getId())).thenReturn(x);
        rateService.findByOriginAndDestination(rate.getOriginCity().getId(), rate.getDestinationCity().getId());
    }

    @Test
    public void testFindByIdOk() throws RateNotExists {
        Rate rate = ObjectCreator.createRate();
        Integer rateId = rate.getId();
        when(rateRepository.findById(rateId)).thenReturn(Optional.of(rate));
        Rate rateFromDb = rateService.findById(rateId);
        assertEquals(rateFromDb, rate);
    }

    @Test(expected = RateNotExists.class)
    public void testFindByIdException() throws RateNotExists {
        Integer rateId = 2;
        when(rateRepository.findById(rateId)).thenReturn(Optional.empty());
        rateService.findById(rateId);
    }

    @Test
    public void testFindByOrigin() {
        Rate rate = ObjectCreator.createRate();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        rates.add(rate);
        when(rateRepository.findByOriginCityId(rate.getOriginCity().getId())).thenReturn(rates);
        List<Rate> ratesFromDb = rateService.findByOrigin(rate.getOriginCity().getId());
        assertEquals(ratesFromDb, rates);
    }

    @Test
    public void testFindByDestination() {
        Rate rate = ObjectCreator.createRate();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        rates.add(rate);
        when(rateRepository.findByDestinationCityId(rate.getDestinationCity().getId())).thenReturn(rates);
        List<Rate> ratesFromDb = rateService.findByDestination(rate.getDestinationCity().getId());
        assertEquals(ratesFromDb, rates);
    }
}
