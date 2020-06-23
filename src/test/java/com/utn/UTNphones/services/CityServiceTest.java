package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.City;
import com.utn.UTNphones.repositories.ICityRepository;
import com.utn.UTNphones.utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CityServiceTest {
    CityService cityService;
    @Mock
    ICityRepository cityRepository;

    @Before
    public void setUp() {
        initMocks(this);
        cityService = new CityService(cityRepository);
    }

    @Test
    public void getByIdOk() {
        City city = ObjectCreator.createCity();
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(city));
        assertEquals(city, cityService.getById(city.getId()));
    }
}
