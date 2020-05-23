package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Province;
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

    @Test
    public void testLoginOk() throws UserExceptions {
        User aux = User.builder().identification("1").password("1234").build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userRepository.findByIdentificationAndPassword(aux.getIdentification(), aux.getPassword())).thenReturn(loggedUser);
        User u = userService.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test
    public void testRegisterOk() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenReturn(registeredUser);
        User u = userService.register(registeredUser);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testRegisterUserDoesntExistException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenThrow(new UserDoesntExist());
        User u = userService.register(registeredUser);
    }


}
