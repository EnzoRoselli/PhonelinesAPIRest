package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.Requests.Login;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.CityExceptions.CityNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeWithIdentificationAlreadyExists;
import com.utn.UTNphones.Services.UserService;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.hibernate.JDBCException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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
        Login aux = ObjectCreator.createClientLoginDTO();
        User loggedUser = User.fromLoginDto(ObjectCreator.createClientLoginDTO());

        when(userController.login(aux)).thenReturn(loggedUser);
        assertEquals(userController.login(aux), loggedUser);
    }

    @Test
    public void testRegisterOk() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        User registeredUser = User.fromDto(user);

        when(userService.register(User.fromDto(user))).thenReturn(registeredUser);

        assertEquals(userController.register(user), registeredUser);
    }

    @Test(expected = UserNotExists.class)
    public void testRegisterUserDoesntExistException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();

        when(userService.register(User.fromDto(user))).thenThrow(new UserNotExists());

        userController.register(user);
    }

    @Test(expected = CityNotExists.class)
    public void testRegisterCityDoesntExistException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1452))) {
        });

        userController.register(user);
    }

    @Test(expected = UserTypeWithIdentificationAlreadyExists.class)
    public void testRegisterUserIdentificationAlreadyExistsException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1062))) {
        });

        userController.register(user);
    }

    @Test(expected = UserTypeNotExists.class)
    public void testRegisterUserTypeDoesntExistException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();

        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1265))) {
        });

        userController.register(user);
    }

    @Test(expected = Exception.class)
    public void testRegisterException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();

        when(userService.register(User.fromDto(user))).thenThrow(new DataAccessException("", new Throwable(new SQLException("", null, 1233))) {
        });
        userController.register(user);
    }

    @Test
    public void testDeleteOk() {
        Integer id=ObjectCreator.createClientUser().getId();
        when(userService.findById(id)).thenReturn(ObjectCreator.createClientUser());
        doNothing().when(userService).delete(id);
        userController.delete(id);

    }

    @Test
    public void testFindByIdOk() throws Exception {
        User user = ObjectCreator.createClientUser();
        Integer id=ObjectCreator.createClientUser().getId();

        when(userService.findById(id)).thenReturn(user);

        assertEquals(userController.findById(id), user);
    }

    @Test(expected = UserNotExists.class)
    public void testFindByIdUserDoesntExistException() throws Exception {
        when(userService.findById(1)).thenThrow(new UserNotExists());
        userController.findById(1);
    }

    @Test
    public void testUpdateOk() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        User userAux = User.fromDto(user);

        userAux.setId(1);
        when(userService.update(userAux)).thenReturn(userAux);
        User u = userController.update(1, user);

        assertEquals(u, userAux);
    }

    @Test(expected = UserTypeWithIdentificationAlreadyExists.class)
    public void testUpdateUserIdentificationAlreadyExistsException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        User userSended = User.fromDto(user);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1062))) {
        });

        userController.update(1, user);
    }

    @Test(expected = UserTypeNotExists.class)
    public void testUpdateUserTypeDoesntExistException() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        User userSended = User.fromDto(user);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1265))) {
        });

        userController.update(1, user);
    }

    @Test(expected = Exception.class)
    public void testUpdateException() throws Exception {
        UserDTO userDto = ObjectCreator.createUserDTO();
        User userSended = User.fromDto(userDto);
        userSended.setId(1);

        when(userService.update(userSended)).thenThrow(new DataAccessException("", new Throwable("Database error")) {
        });
        when(userService.findById(1)).thenReturn(null);

        userController.update(1, userDto);
    }

}
