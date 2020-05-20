package com.utn.UTNphones.Services;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Repositories.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    UserService userService;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp(){
        initMocks(this);
        userService = new UserService(userRepository);
    }

//    @Test
//    public void testLoginOk() throws ParametersException, UserExceptions {
//        User aux = User.builder().identification("1").password("1234").build();
//
//        User loggedUser = User.builder().name("Enzo").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(null).build();
//
//        when(userRepository.findByIdentificationAndPassword(aux)).thenReturn(loggedUser);
//        User u = userController.login(aux);
//
//        assertEquals(u, loggedUser);
//    }

}
