package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    UserController userController;
    @Mock
    UserService userService;

    @Before
    public void setUp(){
        initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testLoginOk() throws ParametersException, UserExceptions {
        User aux = User.builder().identification("1").password("1234").build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userService.login(aux)).thenReturn(loggedUser);
        User u = userController.login(aux);

        assertEquals(u, loggedUser);

//        verify(userService, times(1)).login(aux);
    }

    @Test(expected = ParametersException.class)
    public void testLoginParametersException() throws ParametersException, UserExceptions {
        User aux = User.builder().identification("1").password(null).build();
        userController.login(aux);
    }

    @Test(expected = LogException.class)
    public void testLoginLogException() throws UserExceptions, ParametersException {
        User aux = User.builder().identification("1").password("1234").build();
        when(userService.login(aux)).thenThrow(new LogException());
        userController.login(aux);
    }

//    @Test
//    public void testRegisterOk() throws Exception {
//        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(null).build();
//
//        when(userService.register(registeredUser)).thenReturn(registeredUser);
//        User u = userController.register(registeredUser);
//
//        assertEquals(u, registeredUser);
//    }
}
