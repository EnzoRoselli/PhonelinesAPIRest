package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.Login;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserNotExists;
import com.utn.UTNphones.Repositories.IUserRepository;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    UserService userService;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testLoginOk() throws LogException {
        Login aux = ObjectCreator.createClientLoginDTO();
        User loggedUser = User.fromLoginDto(aux);
        when(userRepository.findByIdentificationAndPasswordAndType(aux.getIdentification(), aux.getPassword(), aux.getType())).thenReturn(Optional.ofNullable(loggedUser));
        User u = userService.login(User.fromLoginDto(aux));

        assertEquals(u, loggedUser);
    }

    @Test(expected = LogException.class)
    public void testLoginLogException() throws LogException {
        User aux = ObjectCreator.createClientUser();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("employee").status(false)
                .identification("1").password("1234").city(City.builder().name("Mar del Plata").build()).build();

        when(userRepository.findByIdentificationAndPasswordAndType(aux.getIdentification(), aux.getPassword(), aux.getType())).thenReturn(Optional.ofNullable(loggedUser));
        User u = userService.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test
    public void testRegisterOk() throws UserNotExists, DataAccessException {
        User userSended = ObjectCreator.createClientUser();
        User registeredUser = ObjectCreator.createClientUser();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.register(userSended);

        assertEquals(u, registeredUser);
    }

    @Test(expected = DataAccessException.class)
    public void testRegisterDataAccessException() throws UserNotExists, DataAccessException {
        User registeredUser = ObjectCreator.createClientUser();

        when(userRepository.save(registeredUser)).thenThrow(new DataAccessException("") {
        });
        User u = userService.register(registeredUser);
    }

    @Test
    public void testDeleteByIdOk() {
        doNothing().when(userRepository).deleteById(12);
        userService.delete(12);
        verify(userRepository, times(1)).deleteById(12);
    }

    @Test
    public void testUpdateOk() throws UserNotExists {
        User userSended = ObjectCreator.createClientUser();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(ObjectCreator.createCity()).build();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.update(userSended);

        assertEquals(u, registeredUser);
    }

    @Test
    public void testFindByIdOk() throws UserNotExists {
        User registeredUser = ObjectCreator.createClientUser();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(registeredUser));

        User u = userService.findById(1);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserNotExists.class)
    public void testFindByIdUserDoesntExistException() throws UserNotExists {
        Optional<User> x = Optional.empty();
        when(userRepository.findById(1)).thenReturn(x);
        User u = userService.findById(1);
    }

}
