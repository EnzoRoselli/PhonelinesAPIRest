package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Repositories.ICityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
        City city = City.builder().id(2).build();
        when(cityRepository.findById(2)).thenReturn(java.util.Optional.ofNullable(city));
        City aux = cityService.getById(2);
        assertEquals(city, aux);

    }
}
