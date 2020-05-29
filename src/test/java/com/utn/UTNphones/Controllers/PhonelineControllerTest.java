package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    public void getByIdOk(){
        Phoneline aux = Phoneline.builder().id(1).number("1234567").type("mobile").status(true)
                .user(new User()).
    }

}
