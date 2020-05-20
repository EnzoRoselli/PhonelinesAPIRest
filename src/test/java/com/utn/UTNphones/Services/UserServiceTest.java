package com.utn.UTNphones.Services;

import com.utn.UTNphones.Repositories.IUserRepository;
import org.junit.Before;
import org.mockito.Mock;

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
