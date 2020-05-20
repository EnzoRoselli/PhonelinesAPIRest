package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.Province;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

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

        User loggedUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userService.login(aux)).thenReturn(loggedUser);
        User u = userController.login(aux);

        assertEquals(u, loggedUser);
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

    @Test
    public void testRegisterOk() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userService.register(registeredUser)).thenReturn(registeredUser);
        User u = userController.register(registeredUser);

        assertEquals(u, registeredUser);
    }

    @Test(expected = ParametersException.class)
    public void testRegisterParametersException() throws Exception {
        User registeredUser = User.builder().name("Enzo").lastname("Roselli").build();
        userController.register(registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testRegisterUserDoesntExistException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userService.register(registeredUser)).thenThrow(new UserDoesntExist());

        userController.register(registeredUser);
    }

//    @Test(expected = DataAccessException.class)
//    public void testRegisterDataAccessException() throws Exception {
//        Province province = Province.builder().name("Buenos Aires").build();
//        City city = City.builder().name("Mar del Palta").prefix("223").province(province).build();
//
//        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(city).build();
//
//        when(userService.register(registeredUser)).thenThrow(new DataAccessException("..."){});
//
//        userController.register(registeredUser);
//    }

    @Test
    public void testDeleteOk() throws Exception {
        when(userService.findByIdentification("1")).thenReturn(null);
        doNothing().when(userService).deleteByIdentification("1");

        userController.delete("1");

        verify(userService, times(1)).deleteByIdentification("1");
    }

    @Test(expected = ParametersException.class)
    public void testDeleteParametersException() throws Exception {

        userController.delete(null);
    }

    @Test(expected = UserDoesntExist.class)
    public void testDeleteUserDoesntExistException() throws Exception {
        when(userService.findByIdentification("1")).thenThrow(new UserDoesntExist());

        userController.delete("1");
    }

    @Test
    public void testUpdateOk() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User updatedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

//        when(userService.update(updatedUser)).thenReturn(updatedUser);
        User u = userController.update(updatedUser);

        assertEquals(u, updatedUser);
    }

    @Test(expected = ParametersException.class)
    public void testUpdateParametersException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User updatedUser = User.builder().id(null).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();
        userController.update(updatedUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testUpdateUserDoesntExistException() throws Exception {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User updatedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

//        when(userService.update(updatedUser)).thenThrow(new UserDoesntExist());

        userController.update(updatedUser);
    }

}