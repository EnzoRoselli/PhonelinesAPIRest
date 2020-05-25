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
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
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
    public void testLoginOk() throws LogException {
        User aux = User.builder().identification("1").password("1234").build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userRepository.findByIdentificationAndPassword(aux.getIdentification(), aux.getPassword())).thenReturn(loggedUser);
        User u = userService.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test(expected = LogException.class)
    public void testLoginLogException() throws LogException {
        User aux = User.builder().identification("1").password("1234").build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userRepository.findByIdentificationAndPassword(aux.getIdentification(), aux.getPassword())).thenReturn(null);
        User u = userService.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test
    public void testRegisterOk() throws Exception {
        City citySended = City.builder().id(1).build();
        User userSended =  User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(citySended).build();

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.register(userSended);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testRegisterUserDoesntExistException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenReturn(null);
        User u = userService.register(registeredUser);
    }

    @Test(expected = DataAccessException.class)
    public void testRegisterDataAccessException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("asd")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenThrow(new DataAccessException("") {});
        User u = userService.register(registeredUser);
    }

    @Test
    public void testDeleteByIdentificationOk() throws Exception {
        doNothing().when(userRepository).deleteByIdentification("1234567");
        userService.deleteByIdentification("1234567");
        verify(userRepository, times(1)).deleteByIdentification("1234567");
    }

    @Test
    public void testUpdateOk() throws Exception {
        City citySended = City.builder().id(1).build();
        User userSended =  User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(citySended).build();

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.update(userSended);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testUpdateUserDoesntExistException() throws Exception {
        City city = City.builder().id(1).build();
        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenReturn(null);
        User u = userService.register(registeredUser);
    }

}
