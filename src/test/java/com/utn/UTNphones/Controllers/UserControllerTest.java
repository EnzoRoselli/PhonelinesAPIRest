package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.*;
import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Province;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

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
//
//    @Test
//    public void testLoginOk() throws ParametersException, UserExceptions {
//        User aux = User.builder().identification("1").password("1234").build();
//
//        User loggedUser = User.builder().name("Enzo").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(null).build();
//
//        when(userService.login(aux)).thenReturn(loggedUser);
//        User u = userController.login(aux);
//
//        assertEquals(u, loggedUser);
//    }
//
//    @Test(expected = ParametersException.class)
//    public void testLoginParametersException() throws ParametersException, LogException {
//        User aux = User.builder().identification("1").password(null).build();
//        userController.login(aux);
//    }
//
//    @Test(expected = LogException.class)
//    public void testLoginLogException() throws LogException, ParametersException {
//        User aux = User.builder().identification("123456").password("1234").build();
//
//        when(userService.login(aux)).thenThrow(new LogException());
//        userController.login(aux);
//    }
//
//    @Test
//    public void testRegisterOk() throws Exception {
//        City city = City.builder().id(1).build();
//
//        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(city).build();
//
//        when(userService.register(registeredUser)).thenReturn(registeredUser);
//        User u = userController.register(registeredUser);
//
//        assertEquals(u, registeredUser);
//    }
//
//    @Test(expected = ParametersException.class)
//    public void testRegisterParametersException() throws Exception {
//        User registeredUser = User.builder().name("Enzo").lastname("Roselli").build();
//        userController.register(registeredUser);
//    }
//
//    @Test(expected = UserDoesntExist.class)
//    public void testRegisterUserDoesntExistException() throws Exception {
//        Province province = Province.builder().name("Buenos Aires").build();
//        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();
//
//        User registeredUser = User.builder().name("Facu").lastname("Roselli").type("client")
//                .identification("1").password("1234").city(city).build();
//
//        when(userService.register(registeredUser)).thenThrow(new UserDoesntExist());
//
//        userController.register(registeredUser);
//    }
//
//    @Test(expected = CityDoesntExist.class)
//    public void testRegisterCityDoesntExistException() throws Exception {
//        City citySended = City.builder().id(11).build();
//        User userSended =  User.builder().name("Enzo").lastname("Coselli").type("client")
//                .identification("9999999").password("1234").city(citySended).build();
//
//        when(userService.register(userSended)).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1452))) {});
//
//        userController.register(userSended);
//    }
//
//    @Test(expected = UserIdentificationAlreadyExists.class)
//    public void testRegisterUserIdentificationAlreadyExistsException() throws Exception {
//        City citySended = City.builder().id(1).build();
//        User userSended =  User.builder().name("Enzo").lastname("Coselli").type("client")
//                .identification("111111").password("1234").city(citySended).build();
//
//        when(userService.register(userSended)).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1062))) {});
//
//        userController.register(userSended);
//    }
//
//    @Test(expected = UserTypeDoesntExist.class)
//    public void testRegisterUserTypeDoesntExistException() throws Exception {
//        City citySended = City.builder().id(1).build();
//        User userSended =  User.builder().id(1).name("Enzo").lastname("Coselli").type("asddas")
//                .identification("123457").password("1234").city(citySended).build();
//
//        when(userService.register(userSended)).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1265))) {});
//
//        userController.register(userSended);
//    }
//
//    @Test(expected = Exception.class)
//    public void testRegisterException() throws Exception {
//        City citySended = City.builder().id(1).build();
//        User userSended =  User.builder().name("Enzo").lastname("Coselli").type("client")
//                .identification("123457").password("1234").city(citySended).build();
//
//        when(userService.register(userSended)).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1))) {});
//
//        userController.register(userSended);
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
    public void testFindByIdOk() throws Exception {
        User user = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(null).build();

        when(userService.findById(1)).thenReturn(user);

        User u = userController.findById(1);

        assertEquals(u, user);
    }

    @Test(expected = UserDoesntExist.class)
    public void testFindByIdUserDoesntExistException() throws Exception {

        when(userService.findById(1)).thenThrow(new UserDoesntExist());
        userController.findById(1);
    }

    @Test
    public void testUpdateOk() throws Exception{
        City city = City.builder().id(1).build();
        User updatedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userService.update(updatedUser)).thenReturn(updatedUser);
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

        when(userService.update(updatedUser)).thenThrow(new UserDoesntExist());

        userController.update(updatedUser);
    }

    @Test(expected = CityDoesntExist.class)
    public void testUpdateCityDoesntExistException() throws Exception {
        City citySended = City.builder().id(11).build();
        User userSended =  User.builder().id(1).name("Enzo").lastname("Coselli").type("client")
                .identification("9999999").password("1234").city(citySended).build();

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("Models.City")) {});
        when(userService.findById(1)).thenReturn(null);

        userController.update(userSended);
    }

    @Test(expected = UserIdentificationAlreadyExists.class)
    public void testUpdateUserIdentificationAlreadyExistsException() throws Exception {
        City citySended = City.builder().id(11).build();
        User userSended =  User.builder().id(1).name("Enzo").lastname("Coselli").type("client")
                .identification("9999999").password("1234").city(citySended).build();

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("for key 'identification_card'")) {});
        when(userService.findById(1)).thenReturn(null);

        userController.update(userSended);
    }

    @Test(expected = UserTypeDoesntExist.class)
    public void testUpdateUserTypeDoesntExistException() throws Exception {
        City citySended = City.builder().id(1).build();
        User userSended =  User.builder().id(1).name("Enzo").lastname("Coselli").type("asddsasda")
                .identification("9999999").password("1234").city(citySended).build();

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("type_user")) {});
        when(userService.findById(1)).thenReturn(null);

        userController.update(userSended);
    }

    @Test(expected = Exception.class)
    public void testUpdateException() throws Exception {
        City citySended = City.builder().id(1).build();
        User userSended =  User.builder().id(1).name("Enzo").lastname("Coselli").type("asddsasda")
                .identification("9999999").password("1234").city(citySended).build();

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("asdsa")) {});
        when(userService.findById(1)).thenReturn(null);

        userController.update(userSended);
    }

}
