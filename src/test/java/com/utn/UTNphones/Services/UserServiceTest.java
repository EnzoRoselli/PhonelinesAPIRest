package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domain.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Domain.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Domain.City;
import com.utn.UTNphones.Domain.Province;
import com.utn.UTNphones.Domain.User;
import com.utn.UTNphones.Repositories.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

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
    public void testRegisterOk() throws UserDoesntExist, DataAccessException {
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
    public void testRegisterUserDoesntExistException() throws UserDoesntExist, DataAccessException {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenReturn(null);
        User u = userService.register(registeredUser);
    }

    @Test(expected = DataAccessException.class)
    public void testRegisterDataAccessException() throws UserDoesntExist, DataAccessException {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("asd")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenThrow(new DataAccessException("") {});
        User u = userService.register(registeredUser);
    }

    @Test
    public void testDeleteByIdentificationOk(){
        doNothing().when(userRepository).deleteByIdentification("1234567");
        userService.deleteByIdentification("1234567");
        verify(userRepository, times(1)).deleteByIdentification("1234567");
    }

    @Test
    public void testUpdateOk() throws UserDoesntExist {
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
    public void testUpdateUserDoesntExistException() throws UserDoesntExist {
        City city = City.builder().id(1).build();
        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenReturn(null);
        User u = userService.register(registeredUser);
    }

    @Test
    public void testFindByIdOk() throws UserDoesntExist {

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(registeredUser));

        User u = userService.findById(1);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testFindByIdUserDoesntExistException() throws UserDoesntExist {
        Optional<User> x = Optional.empty();
        when(userRepository.findById(1)).thenReturn(x);
        User u = userService.findById(1);
    }

    @Test
    public void testFindByIdentificationOk() throws UserDoesntExist {

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User auxUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("111111").password("1234").city(city).build();

        when(userRepository.findByIdentification("111111")).thenReturn(auxUser);

        User u = userService.findByIdentification("111111");

        assertEquals(u, auxUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testFindByIdentificationUserDoesntExistException() throws UserDoesntExist {

        when(userRepository.findByIdentification("654654")).thenReturn(null);
        User u = userService.findByIdentification("654654");
    }

}
