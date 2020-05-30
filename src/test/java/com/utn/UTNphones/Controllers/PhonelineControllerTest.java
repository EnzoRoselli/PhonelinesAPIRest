package com.utn.UTNphones.Controllers;


import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhonelineControllerTest {

    PhonelineController phonelineController;
    @Mock
    PhonelineService phonelineService;
    @Mock
    CityService cityService;

    @Before
    public void setUp(){
        initMocks(this);
        phonelineController = new PhonelineController(phonelineService, cityService);
    }

    @Test
    public void getByIdOk() throws PhonelineDoesntExist {
        Phoneline aux = Phoneline.builder().id(1).number("1111111").type("mobile").status(true)
                .user(new User()).city(new City()).build();

        when(phonelineService.getById(1)).thenReturn(aux);
        Phoneline returnedPhoneline = phonelineController.getById(1);

        assertEquals(aux, returnedPhoneline);
    }
}
