package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class PhonelineControllerTest {

    UserController userController;
    @Mock
    UserService userService;

    @Before
    public void setUp(){
        initMocks(this);
        userController = new UserController(userService);
    }
}
