package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.ClientLoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.Login;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeWithIdentificationAlreadyExists;
import com.utn.UTNphones.Services.UserService;
import org.hibernate.JDBCException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {

    UserController userController;
    @Mock
    UserService userService;


    @Before
    public void setUp() {
        initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testLoginOk() {
        Login aux = ClientLoginDTO.builder().identification("12345678").password("1234").build();

        User loggedUser = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("12345678").password("1234").city(null).build();

        when(userController.login(aux)).thenReturn(loggedUser);
        User u = userController.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test
    public void testRegisterOk() throws Exception {
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(1).build();

        User registeredUser = User.builder().name("Facu").lastname("Roselli").type("client")
                .identification("12345678").password("1234").city(null).build();

        when(userService.register(User.fromDto(user))).thenReturn(registeredUser);
        User u = userController.register(user);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testRegisterUserDoesntExistException() throws Exception {
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(1).build();

        when(userService.register(User.fromDto(user))).thenThrow(new UserDoesntExist());

        userController.register(user);
    }

    @Test(expected = CityDoesntExist.class)
    public void testRegisterCityDoesntExistException() throws Exception {
        City citySended = City.builder().id(11).build();
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(citySended.getId()).build();


        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1452))) {
        });

        userController.register(user);
    }

    @Test(expected = UserTypeWithIdentificationAlreadyExists.class)
    public void testRegisterUserIdentificationAlreadyExistsException() throws Exception {
        City citySended = City.builder().id(1).build();
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(citySended.getId()).build();


        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1062))) {
        });

        userController.register(user);
    }

    @Test(expected = UserTypeDoesntExist.class)
    public void testRegisterUserTypeDoesntExistException() throws Exception {
        City citySended = City.builder().id(1).build();
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(citySended.getId()).build();

        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1265))) {
        });

        userController.register(user);
    }

    @Test(expected = Exception.class)
    public void testRegisterException() throws Exception {
        City citySended = City.builder().id(1).build();
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(citySended.getId()).build();

        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1))) {
        });

        userController.register(user);
    }

    @Test
    public void testDeleteOk() {
        when(userService.findById(1)).thenReturn(User.builder().id(1).build());
        doNothing().when(userService).delete(1);
        userController.delete(1);

        verify(userService, times(1)).delete(1);
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
    public void testUpdateOk() throws Exception {
        City city = City.builder().id(1).build();
        UserDTO user = UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1").password("1234").cityId(city.getId()).build();
        User userAux = User.fromDto(user);
        userAux.setId(1);
        when(userService.update(userAux)).thenReturn(userAux);
        User u = userController.update(1, user);

        assertEquals(u, userAux);
    }

    @Test(expected = UserTypeWithIdentificationAlreadyExists.class)
    public void testUpdateUserIdentificationAlreadyExistsException() throws Exception {
        UserDTO userDto =  UserDTO.builder().name("Facundo").lastname("Mateu").type("client")
                .identification("9999999").password("1234").build();
        User userSended =  User.fromDto(userDto);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1062))) {
        });

        userController.update(1,userDto);
    }

    @Test(expected = UserTypeDoesntExist.class)
    public void testUpdateUserTypeDoesntExistException() throws Exception {
        UserDTO userDto =  UserDTO.builder().name("Facundo").lastname("Mateu").type("abc")
                .identification("9999999").password("1234").build();
        User userSended =  User.fromDto(userDto);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1265))) {
        });

        userController.update(1,userDto);
    }

    @Test(expected = Exception.class)
    public void testUpdateException() throws Exception {
        UserDTO userDto =  UserDTO.builder().name("Facundo").lastname("Mateu").type("abc")
                .identification("9999999").password("1234").build();
        User userSended =  User.fromDto(userDto);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("Database error")) {});
        when(userService.findById(1)).thenReturn(null);

        userController.update(1,userDto);
    }

}
